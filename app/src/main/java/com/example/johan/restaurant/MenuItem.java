package com.example.johan.restaurant;

import java.io.Serializable;

public class MenuItem implements Serializable {
    private String name, description, category, imageUrl;
    private float price;

//    Initiate class in which each dish can be an object
    public MenuItem(String name, String description, String category, String imageUrl, float price) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.imageUrl = imageUrl;
        this.price = price;
    }
//    Initiate getters
    public String getName() {return name;}
    public String getDescription()  {return description;}
    public String getCategory() {return category;}
    public String getImageUrl() {return imageUrl;}
    public float getPrice() { return price;}

//    Initiate setters
    public void setName(String name) {this.name = name;}
    public void setCategory(String category) {this.category = category;}
    public void setDescription(String description) {this.description = description;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
    public void setPrice(float price) {this.price = price;}
}
