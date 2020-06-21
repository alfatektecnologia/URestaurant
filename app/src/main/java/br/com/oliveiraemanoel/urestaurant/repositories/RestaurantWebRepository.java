package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestaurantWebRepository {

    private MutableLiveData<Restaurant> listMutableLiveDataRestaurant;
    private Restaurant restaurantList = new Restaurant();
    Application application;

    public RestaurantWebRepository(Application application){
        this.application=application;
    }

    //method to get the menu data
    public LiveData<Restaurant> getRestaurant(){
        //check if mutablelist has data
        if(listMutableLiveDataRestaurant==null){

            listMutableLiveDataRestaurant = new MutableLiveData<Restaurant>();
            loadRestaurant();
        }

        return listMutableLiveDataRestaurant;
    }

    //using retrofit to get menu data from webservice
    private void loadRestaurant(){

        Retrofit retrofit;
        retrofit = RetrofitClientInstance.getRetrofitInstance();

        GetDataService getDataService = retrofit.create(GetDataService.class);

        Call<Restaurant> call = getDataService.getRestaurant();
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
//
                // generateDataList(response.body());
                listMutableLiveDataRestaurant.setValue(response.body());
                if(listMutableLiveDataRestaurant!=null) {
                    restaurantList = listMutableLiveDataRestaurant.getValue();
                    Log.d("REST_WEB_REPO","NOME RESTAURANTE: " + restaurantList.getName());
                    RestaurantRoomDBRepository restaurantRoomDBRepository = new RestaurantRoomDBRepository(application);
                    restaurantRoomDBRepository.insertItems(restaurantList);
                    //listMutableLiveData.setValue(mUMenuList);
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.d("RESPONSE_FAILURE", t.toString());
            }
        });
    }


}
