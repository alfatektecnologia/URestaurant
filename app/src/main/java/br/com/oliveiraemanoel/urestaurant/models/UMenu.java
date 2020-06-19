package br.com.oliveiraemanoel.urestaurant.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.repositories.databases.RoomTypeConverters;

@Entity(tableName = "menu_table")
public class UMenu {
    @PrimaryKey
    private int id;
    private String name;
    @TypeConverters({RoomTypeConverters.class})
    private List<Item> items;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
