package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.TempCart;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.URestaurantDAO;
import br.com.oliveiraemanoel.urestaurant.repositories.databases.URestaurantRoomDB;


public class RestaurantRoomDBRepository {
    private URestaurantDAO uRestaurantDAO;
    LiveData<Restaurant> restaurantLiveData;
    LiveData<List<UMenu>> allMenu;
    LiveData<List<TempCart>> allCart;

    public RestaurantRoomDBRepository(Application application){
       URestaurantRoomDB db = URestaurantRoomDB.getInstance(application);
        uRestaurantDAO = db.uRestaurantDAO();
        restaurantLiveData = uRestaurantDAO.getAllRestaurants();
        allMenu = uRestaurantDAO.getUMenu();
        allCart = uRestaurantDAO.getAllTempCarts();
    }

    public LiveData<Restaurant> getAllRestaurants(){return restaurantLiveData;}
    public LiveData<List<UMenu>> getAll() {
        return allMenu;
    }
    public LiveData<List<TempCart>> getAllCart(){return allCart;}

    //restaurant
    public void insertItemsRestaurant (Restaurant restaurants) {
        try {
            new RestaurantRoomDBRepository.insertAsyncTaskRestaurant(uRestaurantDAO).execute(restaurants);
        }catch (Exception e){
            Log.d("RoomDbRepository","insertItemsRestaurant_error "+e.toString());
        }

    }

    private static class insertAsyncTaskRestaurant extends AsyncTask<Restaurant, Void, Void> {

        private URestaurantDAO mAsyncTaskDao;

        insertAsyncTaskRestaurant(URestaurantDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            }catch (Exception e){
                Log.d("RoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }
    }

    //menu
    public void insertItemsMenu (List<UMenu> menus) {
        try {
            new insertAsyncTaskMenu(uRestaurantDAO).execute(menus);
        }catch (Exception e){
            Log.d("RoomDbRepository","insertItemsMenu_error "+e.toString());
        }

    }

    private static class insertAsyncTaskMenu extends AsyncTask<List<UMenu>, Void, Void> {

        private URestaurantDAO mAsyncTaskDao;

        insertAsyncTaskMenu(URestaurantDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<UMenu>... params) {
            try {
                mAsyncTaskDao.insertItems(params[0]);
            }catch (Exception e){
                Log.d("RoomDbRepository","InsertItemsMenu_doInBackground_error "+e.toString());

            }

            return null;
        }
    }

    //Tempcart
    public void insert (TempCart cart) {
        try {
            new insertAsyncTaskCart(uRestaurantDAO).execute(cart);
        }catch (Exception e){
            Log.d("RoomDbRepository","insertTempCart_error "+e.toString());
        }

    }

    private static class insertAsyncTaskCart extends AsyncTask<TempCart, Void, Void> {

        private URestaurantDAO mAsyncTaskDao;

        insertAsyncTaskCart(URestaurantDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TempCart... params) {
            try {
                mAsyncTaskDao.insert(params[0]);
            }catch (Exception e){
                Log.d("RoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }
    }

    public void deleteAll(){
        try {
            new insertAsyncTaskDeleteCart(uRestaurantDAO).execute();
        }catch (Exception e){
            Log.d("RoomDbRepository","deleteAllCart_error "+e.toString());
        }

    }

    private static class insertAsyncTaskDeleteCart extends AsyncTask<TempCart, Void, Void> {

        private URestaurantDAO mAsyncTaskDao;

        insertAsyncTaskDeleteCart(URestaurantDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TempCart... params) {
            try {
                mAsyncTaskDao.deleteAllTempCart();
            }catch (Exception e){
                Log.d("RoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }
    }

    public void delete(int index,int itemId){
        try {
            new insertAsyncTaskDelete(uRestaurantDAO).execute(index,itemId);
        }catch (Exception e){
            Log.d("RoomDbRepository","deleteAllCart_error "+e.toString());
        }

    }

    private static class insertAsyncTaskDelete extends AsyncTask<Integer, Void, Void> {

        private URestaurantDAO mAsyncTaskDao;

        insertAsyncTaskDelete(URestaurantDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Integer ... integers) {
            try {
                mAsyncTaskDao.delete(integers[0],integers[1]);
            }catch (Exception e){
                Log.d("RoomDbRepository","doInBackground_error "+e.toString());

            }

            return null;
        }


    }


}
