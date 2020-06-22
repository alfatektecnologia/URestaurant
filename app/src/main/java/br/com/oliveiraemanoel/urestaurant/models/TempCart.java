package br.com.oliveiraemanoel.urestaurant.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "temp_table")
public class TempCart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int itemId;
    private String name;
    private int quantity;
    private double value;
    private double total;

    public TempCart(int itemId, String name, int quantity, double value, double total) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.value = value;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + itemId +
                ", name='" + name + '\'' +
                ", qty='" + quantity + '\'' +
                ", total= " + total +
                '}';
    }
}
