package br.com.oliveiraemanoel.urestaurant.repositories;

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

    @Query("SELECT * FROM Cart")
    public List<Cart> getAllCarts();

    @Query("SELECT * FROM Cart WHERE userId = :userId")
    public List<Cart> getAllCartsByUserId(int userId);

    @Query("SELECT * FROM Cart WHERE date = :date AND userId = :userId")
    public List<Cart> getAllCartsByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM Cart WHERE isClosed=:closed")
    public List<Cart> getAllCartsClosed(boolean closed);

    @Insert(onConflict = REPLACE)
    public void insert(Cart cart);

    @Update
    public void update(Cart cart);

    @Delete
    public void delete(Cart cart);
}
