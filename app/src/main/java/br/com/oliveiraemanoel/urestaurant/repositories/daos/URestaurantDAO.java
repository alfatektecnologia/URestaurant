package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.models.Ordem;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.TempCart;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.models.User;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface URestaurantDAO {

    //cart
    @Query("SELECT * FROM cart_table")
    LiveData<List<Cart>> getAllCarts();

    @Query("SELECT * FROM cart_table WHERE userId = :userId")
    LiveData<List<Cart>> getAllCartsByUserId(int userId);

    @Query("SELECT * FROM cart_table WHERE date = :date AND userId = :userId")
    LiveData<List<Cart>> getAllCartsByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM cart_table WHERE isClosed=:closed")
    LiveData<List<Cart>> getAllCartsClosed(boolean closed);

    @Query("DELETE FROM cart_table")
    void deleteAllCart();

    @Insert(onConflict = REPLACE)
    void insert(Cart cart);

    @Update
    void update(Cart cart);

    @Delete
    void delete(Cart cart);

    //tempCart
    @Query("SELECT * FROM temp_table")
    LiveData<List<TempCart>> getAllTempCarts();

    @Query("SELECT * FROM temp_table WHERE itemId = :itemId")
    LiveData<List<TempCart>> getAllTempCartByItemId(int itemId);

    @Query("DELETE FROM temp_table")
    void deleteAllTempCart();

    @Insert(onConflict = REPLACE)
    void insert(TempCart tempCart);

    @Update
    void update(TempCart tempCart);

    @Delete
    void delete(TempCart tempCart);


    //Ordem
    @Query("SELECT * FROM order_table")
    List<Ordem> getAllOrders();

    @Query("SELECT * FROM order_table WHERE userId = :userId")
    List<Ordem> getAllOrdersByUserId(int userId);

    @Query("SELECT * FROM order_table WHERE date = :date AND userId = :userId")
    List<Ordem> getAllOrdersByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM order_table WHERE isConfirmed=:confirmed")
    List<Ordem> getAllOrderConfirmed(boolean confirmed);

    @Query("DELETE FROM order_table")
    void deleteAllOrder();

    @Insert(onConflict = REPLACE)
    void insert(Ordem order);

    @Update
    void update(Ordem order);

    @Delete
    void delete(Ordem order);

    //Restaurant
    @Query("SELECT * FROM restaurant_table")
    LiveData<Restaurant> getAllRestaurants();

    @Query("DELETE FROM restaurant_table")
    void deleteAllRestaurant();

    @Insert(onConflict = REPLACE)
    void insert(Restaurant restaurant);

    @Update
    void update(Restaurant restaurant);

    //UMenu
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UMenu uMenu);

    @Query("SELECT * FROM menu_table ")
    LiveData<List<UMenu>> getUMenu();

    @Query("DELETE FROM menu_table")
    void deleteAllMenu();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(List<UMenu> uMenus);

    //User
    @Query("SELECT * FROM user_table  ")
    public List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE id = :id")
    public List<User> getUserById(int id);

    @Update()
    public void updateUser(User user);

    //as User is just a mock, delete and insert methods won´t be created
}
