package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Cart;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface CartDAO {

    @Query("SELECT * FROM cart_table")
    LiveData<List<Cart>> getAllCarts();

    @Query("SELECT * FROM cart_table WHERE userId = :userId")
    LiveData<List<Cart>> getAllCartsByUserId(int userId);

    @Query("SELECT * FROM cart_table WHERE date = :date AND userId = :userId")
    LiveData<List<Cart>> getAllCartsByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM cart_table WHERE isClosed=:closed")
    LiveData<List<Cart>> getAllCartsClosed(boolean closed);

    @Query("DELETE FROM cart_table")
    void deleteAll();

    @Insert(onConflict = REPLACE)
    void insert(Cart cart);

    @Update
    void update(Cart cart);



    @Delete
    void delete(Cart cart);
}
