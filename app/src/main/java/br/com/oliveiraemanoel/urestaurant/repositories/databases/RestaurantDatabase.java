package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.oliveiraemanoel.urestaurant.models.Restaurant;

import br.com.oliveiraemanoel.urestaurant.repositories.daos.RestaurantDao;

@Database(entities = {Restaurant.class}, version = 1)
public abstract class RestaurantDatabase extends RoomDatabase {

    private static RestaurantDatabase restaurantInstance; //singleton Instance
    public abstract RestaurantDao restaurantDao();

    public static synchronized RestaurantDatabase getInstance(Context context){

        if(restaurantInstance==null){
            restaurantInstance = Room.databaseBuilder(context.getApplicationContext(),RestaurantDatabase.class,"restaurant_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return restaurantInstance;
    }

    private static Callback restaurantDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new RestaurantDatabase.PopulateDbAsync(restaurantInstance).execute();
        }
    };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final RestaurantDao mDao;

        PopulateDbAsync(RestaurantDatabase db) {
            mDao = db.restaurantDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }

}
