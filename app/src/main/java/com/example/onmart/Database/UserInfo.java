package com.example.onmart.Database;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    int id;

    String UserName;
    String Email;
    String image;

    public UserInfo() {
    }

    @Ignore
    public UserInfo(int id, String userName, String email) {
        this.id = id;
        UserName = userName;
        Email = email;
    }

    public UserInfo(String userName, String email) {
        UserName = userName;
        Email = email;
    }

    public UserInfo(String userName, String email, String image) {
        UserName = userName;
        Email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
