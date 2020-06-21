package br.com.oliveiraemanoel.urestaurant.retrofit;

import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("menu")
    Call <List<UMenu>> getMenu();

    @GET("restaurant")
    Call <Restaurant> getRestaurant();
}
