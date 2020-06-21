package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;


import br.com.oliveiraemanoel.urestaurant.models.Ordem;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrdemDAO {

    @Query("SELECT * FROM order_table")
    List<Ordem> getAllOrders();

    @Query("SELECT * FROM order_table WHERE userId = :userId")
     List<Ordem> getAllOrdersByUserId(int userId);

    @Query("SELECT * FROM order_table WHERE date = :date AND userId = :userId")
     List<Ordem> getAllOrdersByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM order_table WHERE isConfirmed=:confirmed")
    List<Ordem> getAllOrderConfirmed(boolean confirmed);

    @Query("DELETE FROM order_table")
    void deleteAll();

    @Insert(onConflict = REPLACE)
     void insert(Ordem order);

    @Update
    void update(Ordem order);

    @Delete
     void delete(Ordem order);
}
