package br.com.oliveiraemanoel.urestaurant.repositories;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;


import br.com.oliveiraemanoel.urestaurant.models.Orderr;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface OrderDAO {

    @Query("SELECT * FROM Orderr")//got problem using label Order
    public List<Orderr> getAllOrders();

    @Query("SELECT * FROM Orderr WHERE userId = :userId")
    public List<Orderr> getAllOrdersByUserId(int userId);

    @Query("SELECT * FROM Orderr WHERE date = :date AND userId = :userId")
    public List<Orderr> getAllOrdersByUserIdAndDate(Date date, int userId);

    @Query("SELECT * FROM Orderr WHERE isConfirmed=:confirmed")
    public List<Orderr> getAllOrderConfirmed(boolean confirmed);

    @Insert(onConflict = REPLACE)
    public void insert(Orderr order);

    @Update
    public void update(Orderr order);

    @Delete
    public void delete(Orderr order);
}
