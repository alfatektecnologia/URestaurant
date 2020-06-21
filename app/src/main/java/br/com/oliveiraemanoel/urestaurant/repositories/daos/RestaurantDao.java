package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM restaurant_table")
    LiveData<Restaurant> getAllRestaurants();

    @Query("DELETE FROM restaurant_table")
    void deleteAll();

    @Insert(onConflict = REPLACE)
    void insert(Restaurant restaurant);

    @Update
    void update(Restaurant restaurant);
}
