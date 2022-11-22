package ch.zli.m223.controller;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.Bookings;
import ch.zli.m223.service.BookingsService;

@Path("/bookings")
@Tag(name = "Bookings", description = "Handling of bookings")
public class BookingsController {
    @Inject
    BookingsService bookingsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Bookings.", description = "Returns a list of all bookings.")
    public List<Bookings> index() {
        return bookingsService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new booking.", description = "Creates a new booking and returns the newly added booking.")
    public Bookings create(Bookings bookings) {
        return bookingsService.createBooking(bookings);
    }

    @DELETE
    @Path("/{bookingId}")
    @Operation(summary = "Deletes a booking.", description = "Deletes a booking by its id.")
    public void delete(@PathParam("bookingId") Long id) {
        bookingsService.deleteBooking(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a booking.", description = "Updates a booking.")
    public Bookings update(Bookings bookings) {
        return bookingsService.updateBooking(bookings);
    }
}
