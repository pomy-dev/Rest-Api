/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.models.Comments;
import com.models.Posts;
import com.models.Users;

/**
 *
 * @author Pomy
 */
public class DataAccessObject {

    public DataAccessObject() {
    }
    
    //    Connection method
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        //Register the Driver.
          Class.forName("com.mysql.cj.jdbc.Driver");  
        
       return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/crudapi", "root", "");
    }

//    user dao from the beggining
    public List<Users> getAllUsers(int page, int size) {
        List<Users> userList = new ArrayList<>();
        int offset  = (page - 1) * size;
        // Implement the logic to fetch all users from the database
        String query = "select * from users limit ? offset ?";
        
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, size);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String userName = rs.getString(3);
                String email = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                
                Users user = new Users(id,name,userName,email,address,phone);
                userList.add(user);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    public Users getUser(int id) {
        // Implement the logic to fetch a user by id from the database
        Users user = null;
         String query = "select * from users where id = ?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                String name = rs.getString(2);
                String userName = rs.getString(3);
                String email = rs.getString(4);
                String address = rs.getString(5);
                String phone = rs.getString(6);
                
                 user = new Users(id,name,userName,email,address,phone); 
            }
        }catch(Exception  e){
            e.printStackTrace();
        }
        return user;
    }

    public int createUser(Users user) {
        // Implement the logic to create a new user in the database
        String query = "INSERT INTO Users(id, name, userName, email, address, phone) values(?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setDouble(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getUsername());
            st.setString(4, user.getEmail());
            st.setString(5, user.getAddress());
            st.setString(6, user.getPhone());
            st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    public void updateUser(int id, Users user) throws ClassNotFoundException, SQLException {
        // Implement the logic to update an existing user in the database
        String query = "update users set name=?, username=?, email=?, address=?, phone=? where id=?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getEmail());
            st.setString(4, user.getAddress());
            st.setString(5, user.getPhone());
            st.setInt(6, user.getId());
            st.executeUpdate();
        }
    }

    public void deleteUser(int id) throws ClassNotFoundException, SQLException {
        // Implement the logic to delete a user from the database
        String query = "delete from users where id =?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
           st.executeUpdate(); 
        }
    }
    
//  the end of  user dao
    
//    the post dao at start
    
    public List<Posts> getAllPosts(int page, int size) {
        List<Posts> postList = new ArrayList<>();
        int offset  = (page - 1) * size;
        // Implement the logic to fetch all posts from the database
        String query = "select * from posts limit ? offset ?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, size);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int userId = rs.getInt(2);
                String title = rs.getString(3);
                String body = rs.getString(4);
                
                Posts post = new Posts(id,userId,title,body);
                postList.add(post);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return postList;
    }

    public Posts getPost(int id) {
        // Implement the logic to fetch a post by id from the database
        Posts post = null;
         String query = "select * from posts where id = ?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                int userId = rs.getInt(2);
                String title = rs.getString(3);
                String body = rs.getString(4);
                
                 post = new Posts(id,userId,title,body); 
            }
        }catch(Exception  e){
            e.printStackTrace();
        }
        return post;
    }

    public int createPost(Posts post) {
        // Implement the logic to create a new post in the database
        String query = "insert into posts(id, userid, title, body) values(?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, post.getId());
            st.setInt(2, post.getUserId());
            st.setString(3, post.getTitle());
            st.setString(4, post.getBody());
            st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    public void updatePost(int id, Posts post) throws ClassNotFoundException, SQLException {
        // Implement the logic to update an existing post in the database
        String query = "update posts set userid=?, title=?, body=? where id=?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, post.getUserId());
            st.setString(2, post.getTitle());
            st.setString(3, post.getBody());
            st.setInt(4, post.getId());
            st.executeUpdate();
        }
    }

    public void deletePost(int id) throws ClassNotFoundException, SQLException {
        // Implement the logic to delete a post from the database
        String query = "delete from posts where id =?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
           st.executeUpdate(); 
        }
    }
    
//    the end of user dao
    
//    the comments dao start
    
    public List<Comments> getAllComments(int page, int size) {
        List<Comments> commentList = new ArrayList<>();
        int offset  = (page - 1) * size;
        // Implement the logic to fetch all comments from the database
        String query = "select * from comments limit ? offset ?";
        
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, size);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                int userid = rs.getInt(2);
                int postid = rs.getInt(3);
                String email = rs.getString(4);
                String body = rs.getString(5);
                
                Comments comment = new Comments(id,userid,postid,email,body);
                commentList.add(comment);
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return commentList;
    }

    public Comments getComment(int id) {
        // Implement the logic to fetch a comment by id from the database
        Comments comment = null;
         String query = "select * from comments where id = ?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                int userid = rs.getInt(2);
                int postid = rs.getInt(3);
                String email = rs.getString(4);
                String body = rs.getString(5);
                
                 comment = new Comments(id,userid,postid,email,body); 
            }
        }catch(Exception  e){
            e.printStackTrace();
        }
        return comment;
    }

    public int createComment(Comments comment) {
        // Implement the logic to create a new comment in the database
        String query = "insert into comments(id, name, userName, email, address, phone) values(?, ?, ?, ?, ?, ?)";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, comment.getId());
            st.setInt(2, comment.getUserid());
            st.setInt(3, comment.getPostId());
            st.setString(4, comment.getEmail());
            st.setString(5, comment.getBody());
            st.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return 1;
    }

    public void updateComment(int id, Comments comment) throws ClassNotFoundException, SQLException {
        // Implement the logic to update an existing comment in the database
        String query = "update comments set userid=?, postid=?, email=?, comment=? where id=?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, comment.getUserid());
            st.setInt(2, comment.getPostId());
            st.setString(3, comment.getEmail());
            st.setString(4, comment.getBody());
            st.setInt(5, comment.getId());
            st.executeUpdate();
        }
    }

    public void deleteComment(int id) throws ClassNotFoundException, SQLException {
        // Implement the logic to delete a comment from the database
        String query = "DELETE FROM comments WHERE id =?";
        try(Connection con = getConnection(); PreparedStatement st = (PreparedStatement) con.prepareStatement(query)){
            st.setInt(1, id);
           st.executeUpdate(); 
        }
    }
    
//    the comments dao end
}
