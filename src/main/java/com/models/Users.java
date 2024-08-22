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
@Schema(description = "User entity")
public class Users {
    @Schema(description = "User ID", example = "1")
    private int id;
    
    @Schema(description = "User name", example = "John Doe")
    private String name;
    
    @Schema(description = "User username", example = "Mandla Nxumalo")
    private String username;
    
    @Schema(description = "User email", example = "john.doe@gmail.com")
    private String email;
    
    @Schema(description = "User address", example = "Lundzi")
    private String address;
    
    @Schema(description = "User phone", example = "76123456")
    private String phone;

    public Users() {
    }

    public Users(int id, String name, String username, String email, String address, String phone) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
