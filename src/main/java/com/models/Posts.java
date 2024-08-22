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
@Schema(description = "Post entity")
public class Posts {
    @Schema(description = "Post ID", example = "1")
    private int id;
        
    @Schema(description = "User Id", example = "1")
    private int userId;
        
    @Schema(description = "Post Title", example = "Title")
    private String title;
        
    @Schema(description = "Post body", example = "Too many posts recieved")
    private String body;

    public Posts() {
    }

    public Posts(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    
}
