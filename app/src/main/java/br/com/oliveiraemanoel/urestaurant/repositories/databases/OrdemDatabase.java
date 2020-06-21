package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.oliveiraemanoel.urestaurant.models.Ordem;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.OrdemDAO;

@Database(entities ={Ordem.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class OrdemDatabase extends RoomDatabase {
    private static OrdemDatabase INSTANCE;

    public abstract OrdemDAO orderDAO();

    public static synchronized OrdemDatabase getInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, OrdemDatabase.class,"order_table")
                    .fallbackToDestructiveMigration()
                    .build();

        }

        return INSTANCE;
    }

    private static Callback ordemDBcallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final OrdemDAO mDao;

        PopulateDbAsync(OrdemDatabase db) {
            mDao = db.orderDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }

}
