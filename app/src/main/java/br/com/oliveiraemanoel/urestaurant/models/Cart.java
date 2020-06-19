package br.com.oliveiraemanoel.urestaurant.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.repositories.databases.RoomTypeConverters;

@Entity(tableName = "cart_table")
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int userId;
    @TypeConverters(RoomTypeConverters.class)
    private List<Orderr> ordemList;
    private int totalItems;
    private double totalValue;
    private Date date;
    private boolean isClosed;

    public void setId(int id) {
        this.id = id;
    }

    public void setOrdemList(List<Orderr> ordemList) {
        this.ordemList = ordemList;
    }

    public List<Orderr> getOrdemList() {
        return ordemList;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

   /* public List<Orderr> getOrderList() {
        return ordemList;
    }

    public void setOrderList(List<Orderr> orderList) {
        this.ordemList = orderList;
    }*/

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
