package ch.zli.m223.service;

import javax.inject.Inject;

// import java.util.Arrays;
// import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.model.Users;
//import ch.zli.m223.service.UsersService;
import io.smallrye.jwt.build.Jwt;

public class AuthService {

  @Inject
    UsersService usersService;

  /**
   * Generate JWT token
   */
  public String generateToken(Users users) {
    String token = Jwt.issuer("https://example.com/issuer")
        .upn("jdoe@quarkus.io")
        .groups("Admin")
        .claim(Claims.birthdate.name(), "2001-07-13")
        .sign();
    return token;
  }

}