package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.repositories.CartDAO;

@Database(entities ={Cart.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase cartDatabase;

    public abstract CartDAO createCartDAO();

    public static CartDatabase getInstance(Context context){
        if(cartDatabase==null){
            cartDatabase = Room.databaseBuilder(context,CartDatabase.class,"cart_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }

    return cartDatabase;
    }

}
