package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.UMenuTableDao;
import br.com.oliveiraemanoel.urestaurant.repositories.databases.UMenuRoomDatabase;

public class MenuRoomDBRepository {
    private UMenuTableDao uMenuTableDao;
    LiveData<List<UMenu>> allMenu;


    public MenuRoomDBRepository(Application application){
        UMenuRoomDatabase db = UMenuRoomDatabase.getInstance(application);
        uMenuTableDao = db.menuTableDao();
        allMenu = uMenuTableDao.getUMenu();
    }

    public LiveData<List<UMenu>> getAll() {
        return allMenu;
    }
    public void insertItems (List<UMenu> menus) {
        try {
            new insertAsyncTask(uMenuTableDao).execute(menus);
        }catch (Exception e){
            Log.d("MenuRoomDbRepository","getAll_error "+e.toString());
        }

    }

    private static class insertAsyncTask extends AsyncTask<List<UMenu>, Void, Void> {

        private UMenuTableDao mAsyncTaskDao;

        insertAsyncTask(UMenuTableDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<UMenu>... params) {
            try {
                mAsyncTaskDao.insertItems(params[0]);
            }catch (Exception e){
                Log.d("MenuRoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }
    }
}
