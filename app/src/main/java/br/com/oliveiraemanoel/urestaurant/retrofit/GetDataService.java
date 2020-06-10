package br.com.oliveiraemanoel.urestaurant.retrofit;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Menu;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("menu")
    Call <List<Menu>> getMenu();

    @GET("restaurant")
    Call <List<Restaurant>> getRestaurant();
}
