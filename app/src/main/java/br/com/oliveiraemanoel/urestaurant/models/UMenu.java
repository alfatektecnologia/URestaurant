package br.com.oliveiraemanoel.urestaurant.models;

import java.util.List;

public class UMenu {

    private int id;
    private String name;
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



}
