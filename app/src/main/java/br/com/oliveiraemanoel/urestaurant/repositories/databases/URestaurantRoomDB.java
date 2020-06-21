package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.models.Ordem;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.models.User;

import br.com.oliveiraemanoel.urestaurant.repositories.daos.URestaurantDAO;

@Database(entities = {Cart.class, Ordem.class, Restaurant.class, User.class, UMenu.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class URestaurantRoomDB extends RoomDatabase {

    private static URestaurantRoomDB INSTANCE; //singleton
    public abstract URestaurantDAO uRestaurantDAO();

    public static synchronized URestaurantRoomDB getInstance(Context context){

        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),URestaurantRoomDB.class,"restaurant_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(uRestaurantRoomDBCallback)
                    .build();
        }
        return INSTANCE;
    }

    public static Callback uRestaurantRoomDBCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final URestaurantDAO mDao;

        PopulateDbAsync(URestaurantRoomDB db) {
            mDao = db.uRestaurantDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAllRestaurant();
            return null;
        }
    }



}
