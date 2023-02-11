package com.example.onmart;

public class Product {
    private int id;
    private  int price;
   private  String title,description,creationAt,updatedAt,image,name;
   private String[] arrImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product(int id, int price, String title, String description, String creationAt, String updatedAt, String image, String name) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.creationAt = creationAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.name = name;
    }

    public Product(int id, int price, String title, String description, String creationAt, String updatedAt, String image, String[] arrImage) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.creationAt = creationAt;
        this.updatedAt = updatedAt;
        this.image = image;
        this.arrImage = arrImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreationAt() {
        return creationAt;
    }

    public void setCreationAt(String creationAt) {
        this.creationAt = creationAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getArrImage() {
        return arrImage;
    }

    public void setArrImage(String[] arrImage) {
        this.arrImage = arrImage;
    }

    public Product(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
