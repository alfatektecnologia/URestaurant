package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.oliveiraemanoel.urestaurant.models.Product;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.ProductDAO;

@Database(entities ={Product.class},version = 1)
public abstract class ProductDatabase extends RoomDatabase {
    private static ProductDatabase productDatabase;

    public abstract ProductDAO createProductDAO();

    public static ProductDatabase getInstance(Context context){
        if(productDatabase==null){
            productDatabase = Room.databaseBuilder(context,ProductDatabase.class,"product_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return productDatabase;
    }

}
