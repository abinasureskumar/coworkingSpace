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

import ch.zli.m223.model.Rooms;
import ch.zli.m223.service.RoomsService;

@Path("/rooms")
@Tag(name = "Rooms", description = "Handling of rooms")
public class RoomsController {
    @Inject
    RoomsService roomsService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all rooms.", description = "Returns a list of all rooms.")
    public List<Rooms> index() {
        return roomsService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new room.", description = "Creates a new room and returns the newly added room.")
    public Rooms create(Rooms rooms) {
        return roomsService.createRoom(rooms);
    }

    @DELETE
    @Path("/{roomId}")
    @Operation(summary = "Deletes a room.", description = "Deletes a room by its id.")
    public void delete(@PathParam("roomId") Long id) {
        roomsService.deleteRoom(id);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates a room.", description = "Updates a room.")
    public Rooms update(Rooms rooms) {
        return roomsService.updateRoom(rooms);
    }
}
