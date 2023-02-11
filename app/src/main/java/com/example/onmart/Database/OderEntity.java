package com.example.onmart.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OderEntity {

    @PrimaryKey(autoGenerate = true)
    int id ;

    String Name;
    String Email;
    String Title;
    String Image;
    int price;

    public OderEntity() {
    }

    public OderEntity(int id, String name, String email, String title, String image, int price) {
        this.id = id;
        Name = name;
        Email = email;
        Title = title;
        Image = image;
        this.price = price;
    }

    public OderEntity(String name, String email, String title, String image, int price) {
        Name = name;
        Email = email;
        Title = title;
        Image = image;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
