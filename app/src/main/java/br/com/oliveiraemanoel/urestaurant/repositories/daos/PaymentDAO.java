package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Payment;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PaymentDAO {

    @Query("SELECT * FROM Payment")
    public List<Payment> getAllPayments();

    @Query("SELECT * FROM Payment WHERE userId = :userId")
    public List<Payment> getAllPaymentsByUserId(int userId);

    @Insert(onConflict = REPLACE)
    public void insert(Payment payment);

    @Update
    public void update(Payment payment);

    @Delete
    public void delete(Payment payment);

}
