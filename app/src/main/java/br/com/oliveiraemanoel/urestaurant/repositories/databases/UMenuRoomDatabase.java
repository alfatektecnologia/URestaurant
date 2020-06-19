package br.com.oliveiraemanoel.urestaurant.repositories.databases;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.UMenuTableDao;

@Database(entities = {UMenu.class},version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class UMenuRoomDatabase extends RoomDatabase {
    public abstract UMenuTableDao menuTableDao();
    private static UMenuRoomDatabase INSTANCE;

    public static synchronized UMenuRoomDatabase getInstance(Context context){

        if(INSTANCE==null){

            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UMenuRoomDatabase.class,"menu_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    private static Callback sMenuRoomDatabase = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UMenuTableDao mDao;

        PopulateDbAsync(UMenuRoomDatabase db) {
            mDao = db.menuTableDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            return null;
        }
    }
}

