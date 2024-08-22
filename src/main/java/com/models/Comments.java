/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Pomy
 */
@Schema(description = "Comment entity")
public class Comments {
    @Schema(description = "Comment ID", example = "1")
    private int id;
            
    @Schema(description = "User Id", example = "1")
    private int userid;
            
    @Schema(description = "Post Id", example = "1")
    private int postId;
    
    @Schema(description = "User email", example = "1")
    private String email;
    
    @Schema(description = "Comment body", example = "Too many posts recieved")
    private String body;

    public Comments(int id, int userid, int postId, String email, String body) {
        this.id = id;
        this.userid = userid;
        this.postId = postId;
        this.email = email;
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}
