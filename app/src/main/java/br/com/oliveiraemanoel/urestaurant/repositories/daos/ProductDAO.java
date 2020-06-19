package br.com.oliveiraemanoel.urestaurant.repositories.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Product;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product")
    public List<Product> getAllProducts();

    @Query("SELECT * FROM Product WHERE id = :id")
    public List<Product> getProductById(int id);

    @Query("SELECT * FROM Product WHERE groupId =  :groupID")
    public List<Product> getProductByGroupId(int groupID);

    @Insert(onConflict = REPLACE)
    public void insert(Product product);

    @Update
    public void update(Product product);

    @Delete
    public void delete(Product product);

}
