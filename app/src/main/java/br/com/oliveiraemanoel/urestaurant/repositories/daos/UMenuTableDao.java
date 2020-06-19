package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.UMenu;


@Dao
public interface UMenuTableDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UMenu uMenu);

    @Query("SELECT * FROM menu_table ")
    LiveData<List<UMenu>> getUMenu();

    @Query("DELETE FROM menu_table")
    void deleteAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItems(List<UMenu> uMenus);

}
