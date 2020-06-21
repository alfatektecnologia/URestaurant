package br.com.oliveiraemanoel.urestaurant.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "restaurant_table")
public class Restaurant {
     @PrimaryKey
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDelivery_fee(double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public void setMinimum_order_price(int minimum_order_price) {
        this.minimum_order_price = minimum_order_price;
    }

    public double getDelivery_fee() {
        return delivery_fee;
    }

    public int getMinimum_order_price() {
        return minimum_order_price;
    }
}
