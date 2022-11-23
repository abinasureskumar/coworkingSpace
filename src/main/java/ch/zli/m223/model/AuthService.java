package ch.zli.m223.model;

// import java.util.Arrays;
// import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;

public class AuthService {
    /**
     * Generate JWT token
     */
    public String generateToken(Users users) {
        String token =
           Jwt.issuer("https://example.com/issuer") 
             .upn("jdoe@quarkus.io") 
             .groups("Admin") 
             .claim(Claims.birthdate.name(), "2001-07-13") 
           .sign();
        return token;
    }

}
