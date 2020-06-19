package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.CartDAO;
import br.com.oliveiraemanoel.urestaurant.repositories.databases.CartDatabase;

public class CartRepository {

    private CartDAO cartDAO;
    private CartDatabase cartDatabase;
    private LiveData<List<Cart>> allCarts;

    public CartRepository(Application application){

        cartDatabase = CartDatabase.getInstance(application);
        allCarts = cartDAO.getAllCarts();
           }

}
