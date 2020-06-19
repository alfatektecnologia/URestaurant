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
import br.com.oliveiraemanoel.urestaurant.repositories.daos.CartDAO;

@Database(entities ={Cart.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class CartDatabase extends RoomDatabase {
    private static CartDatabase cartDatabase;//singleton of an instance of CartDatabase

    public abstract CartDAO cartDAO();

    public static synchronized CartDatabase getInstance(Context context){
        if(cartDatabase==null){
                    cartDatabase = Room.databaseBuilder(context.getApplicationContext(),CartDatabase.class,"cart_Database")
                            .fallbackToDestructiveMigration()
                            .addCallback(cartDatabaseCallback)
                            .build();

        }

    return cartDatabase;
    }

    private static Callback cartDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(cartDatabase).execute();
        }
    };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CartDAO mDao;

        PopulateDbAsync(CartDatabase db) {
            mDao = db.cartDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }
}
