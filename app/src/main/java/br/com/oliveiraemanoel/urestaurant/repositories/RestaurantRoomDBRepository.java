package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;



import br.com.oliveiraemanoel.urestaurant.models.Restaurant;

import br.com.oliveiraemanoel.urestaurant.repositories.daos.RestaurantDao;

import br.com.oliveiraemanoel.urestaurant.repositories.databases.RestaurantDatabase;


public class RestaurantRoomDBRepository {
    private RestaurantDao restaurantDao;
    LiveData<Restaurant> restaurantLiveData;

    public RestaurantRoomDBRepository(Application application){
        RestaurantDatabase db = RestaurantDatabase.getInstance(application);
        restaurantDao = db.restaurantDao();
        restaurantLiveData = restaurantDao.getAllRestaurants();
    }

    public LiveData<Restaurant> getAllRestaurants(){return restaurantLiveData;}


    public void insertItems (Restaurant restaurants) {
        try {
            new RestaurantRoomDBRepository.insertAsyncTask(restaurantDao).execute(restaurants);
        }catch (Exception e){
            Log.d("RestauRoomDbRepository","getAllRestaurant_error "+e.toString());
        }

    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            }catch (Exception e){
                Log.d("MenuRoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }
    }
}
