package br.com.oliveiraemanoel.urestaurant.repositories;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.User;

@Dao
public interface UserDAO {

    @Query("SELECT * FROM User  ")
    public List<User> getAllUsers();

    @Query("SELECT * FROM User WHERE id = :id")
    public List<User> getUserById(int id);

    @Update()
    public void update(User user);

    //as User is just a mock, delete and insert methods won´t be created
}
