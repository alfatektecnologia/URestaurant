package br.com.oliveiraemanoel.urestaurant.repositories;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.adapters.MenuItemAdapter;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UMenuWebRepository {
    private List<UMenu> mUMenuList = new ArrayList<>();
    private List<UMenu> mUMenuListItem = new ArrayList<>();
    private List<Restaurant> restaurantList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();

    //data that will be mutable asynchronously
    private MutableLiveData<List<UMenu>> listMutableLiveData;
    private MutableLiveData<List<Restaurant>> listMutableLiveDataRestaurant;
    private MutableLiveData<List<Item>> listMutableLiveDataMenuItem;
    private MenuItemAdapter itemAdapter;

    Application application;
    public UMenuWebRepository(Application application){
        this.application = application;
    }

    //method to get the menu data
    public LiveData<List<UMenu>> getMenu(){
        //check if mutablelist has data
        if(listMutableLiveData==null){

            listMutableLiveData = new MutableLiveData<List<UMenu>>();
            loadMenu();
        }

        return listMutableLiveData;
    }

    //using retrofit to get menu data from webservice
    private void loadMenu(){

        Retrofit retrofit;
        retrofit = RetrofitClientInstance.getRetrofitInstance();

        GetDataService getDataService = retrofit.create(GetDataService.class);

        Call<List<UMenu>> call = getDataService.getMenu();
        call.enqueue(new Callback<List<UMenu>>() {
            @Override
            public void onResponse(Call<List<UMenu>> call, Response<List<UMenu>> response) {
//
                // generateDataList(response.body());
               listMutableLiveData.setValue(response.body());
                if(listMutableLiveData!=null) {
                    mUMenuList = listMutableLiveData.getValue();

                    MenuRoomDBRepository menuRoomDBRepository = new MenuRoomDBRepository(application);
                    menuRoomDBRepository.insertItems(mUMenuList);
                    //listMutableLiveData.setValue(mUMenuList);
                }
            }

            @Override
            public void onFailure(Call<List<UMenu>> call, Throwable t) {
                Log.d("RESPONSE_FAILURE", t.toString());
            }
        });
    }



}

