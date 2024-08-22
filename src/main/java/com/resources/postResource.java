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
import com.models.Posts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author Pomy
 */
@Path("posts")
@Tag(name = "Post", description = "Post management Section")
public class postResource {
    private final DataAccessObject postDAO = new DataAccessObject();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get all Posts", responses = {
        @ApiResponse(responseCode = "200", description = "Posts List")
    })
    public Response getAllPosts(
        @QueryParam("page") @DefaultValue("1") int page, 
        @QueryParam("size") @DefaultValue("10") int size
    ) {
        List<Posts> posts = postDAO.getAllPosts(page,size);
        if (posts.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No posts found")
                           .build();
        }
        return Response.ok(posts).build();
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Get a post by ID", responses = {
        @ApiResponse(responseCode = "200", description = "Post details"),
        @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public Response getPost(@PathParam("id") int id) {
        Posts post = postDAO.getPost(id);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Comment not found")
                           .build();
        }
        return Response.ok(post).build();
    }

    @POST
    @Path("/create")   
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Create a new post", responses = {
        @ApiResponse(responseCode = "201", description = "post created")
    })
    public Response createPost(Posts post) {
        int id = postDAO.createPost(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @PUT
    @Path("/{id}")   
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Update a post", responses = {
        @ApiResponse(responseCode = "200", description = "Post updated"),
        @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public Response updatePost(@PathParam("id") int id, Posts post) throws ClassNotFoundException, SQLException {
        postDAO.updatePost(id, post);
        return Response.ok(post).build();
    }

    @DELETE
    @Path("/{id}")   
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Operation(summary = "Delete a post", responses = {
        @ApiResponse(responseCode = "204", description = "Post deleted"),
        @ApiResponse(responseCode = "404", description = "Post not found")
    })
    public Response deletePost(@PathParam("id") int id) throws ClassNotFoundException, SQLException {
        postDAO.deletePost(id);
        return Response.noContent().build();
    }
}
