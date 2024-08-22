/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resources;

import com.dao.DataAccessObject;
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
import com.models.Comments;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author Pomy
 */
@Path("/comments")
@Tag(name = "Comments", description = "Comment management Section")
public class commentResource {
    private final DataAccessObject commentDAO = new DataAccessObject();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get all Comments", responses = {
        @ApiResponse(responseCode = "200", description = "Comment List")
    })
    public Response getAllComments(
         @QueryParam("page") @DefaultValue("1") int page, 
         @QueryParam("size") @DefaultValue("10") int size
    ) {
        List<Comments> comments = commentDAO.getAllComments(page, size);
        if (comments.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No users found")
                           .build();
        }
        return Response.ok(comments).build();
    }

    @GET
    @Path("/{id}")   
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get a Comment by ID", responses = {
        @ApiResponse(responseCode = "200", description = "comment details"),
        @ApiResponse(responseCode = "404", description = "comment not found")
    })
    public Response getComment(@PathParam("id") int id) {
        Comments comment = commentDAO.getComment(id);
        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Comment not found")
                           .build();
        }
        return Response.ok(comment).build();
    }

    @POST
    @Path("create")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Create a new comment", responses = {
        @ApiResponse(responseCode = "201", description = "comment created")
    })
    public Response createComment(Comments comment) {
        int id = commentDAO.createComment(comment);
        return Response.status(Response.Status.CREATED).entity(comment).build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Update a comment", responses = {
        @ApiResponse(responseCode = "200", description = "comment updated"),
        @ApiResponse(responseCode = "404", description = "comment not found")
    })
    public Response updateComment(@PathParam("id") int id, Comments comment) throws ClassNotFoundException, SQLException {
        commentDAO.updateComment(id, comment);
        return Response.ok(comment).build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Delete a comment", responses = {
        @ApiResponse(responseCode = "204", description = "Comment deleted"),
        @ApiResponse(responseCode = "404", description = "Comment not found")
    })
    public Response deleteComment(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        commentDAO.deleteComment(id);
        return Response.noContent().build();
    }
}
