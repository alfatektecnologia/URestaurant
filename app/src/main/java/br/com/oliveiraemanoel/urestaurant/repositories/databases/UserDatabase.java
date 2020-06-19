package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.oliveiraemanoel.urestaurant.models.User;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.UserDAO;

@Database(entities ={User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase userDatabase;

    public abstract UserDAO createUserDAO();

    public static UserDatabase getInstance(Context context){
        if(userDatabase==null){
            userDatabase = Room.databaseBuilder(context,UserDatabase.class,"user_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return userDatabase;
    }

}