package br.com.oliveiraemanoel.urestaurant.models;

public class Restaurant {
     private int id; //"id": 999,
     private String name;// "name": "Super Taste Food",
     private double delivery_fee; //"delivery_fee": 5.99,
     private int minimum_order_price;//"minimum_order_price": 50

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public int getMinimum_order_price() {
        return minimum_order_price;
    }
}
