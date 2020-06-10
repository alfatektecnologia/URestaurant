package br.com.oliveiraemanoel.urestaurant.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//mock user used for test purpose only
@Entity
public class User {
    @PrimaryKey
    private int id = 123456;
    private String name= "Paul Smith da Silva";
    private double initialCredit=100.00;
    private double availableCredit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }

    public String getName() {
        return name;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public double getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(double availableCredit) {
        this.availableCredit = availableCredit;
    }
}
