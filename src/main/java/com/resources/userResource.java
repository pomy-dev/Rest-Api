/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resources;

import com.dao.DataAccessObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
import com.models.Users;

/**
 *
 * @author Pomy
 */
@Path("/users")
@Tag(name = "User", description = "User management Section")
public class userResource {

    private final DataAccessObject userDAO = new DataAccessObject();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get all users", responses = {
        @ApiResponse(responseCode = "200", description = "List of users")
    })
    public Response getAllUsers(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        List<Users> users = userDAO.getAllUsers(page, size);
        if (users.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No users found")
                           .build();
        }
        return Response.ok(users).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get a user by ID", responses = {
        @ApiResponse(responseCode = "200", description = "User details"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Response getUser(@PathParam("id") int id) {
        Users user = userDAO.getUser(id);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Users not found")
                           .build();
        }
        return Response.ok(user).build();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Create a new user", responses = {
        @ApiResponse(responseCode = "201", description = "User created")
    })
    public Response createUser(Users user) {
        int id = userDAO.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Update a user", responses = {
        @ApiResponse(responseCode = "200", description = "User updated"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Response updateUser(@PathParam("id") int id, Users user) throws ClassNotFoundException, SQLException {
        userDAO.updateUser(id, user);
        return Response.ok(user).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Delete a user", responses = {
        @ApiResponse(responseCode = "204", description = "User deleted"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public Response deleteUser(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        userDAO.deleteUser(id);
        return Response.noContent().build();
    }
}
