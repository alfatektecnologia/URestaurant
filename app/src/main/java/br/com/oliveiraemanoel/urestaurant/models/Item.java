package br.com.oliveiraemanoel.urestaurant.models;

public class Item{
    private int id;
    private String name;
    private double price;
    private String description;
    private String image_url;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }
}