package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.oliveiraemanoel.urestaurant.models.Orderr;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.OrderDAO;

@Database(entities ={Orderr.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class OrderDatabase extends RoomDatabase {
    private static OrderDatabase orderDatabase;

    public abstract OrderDAO createOrderDAO();

    public static OrderDatabase getInstance(Context context){
        if(orderDatabase==null){
            orderDatabase = Room.databaseBuilder(context,OrderDatabase.class,"order_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return orderDatabase;
    }

}
