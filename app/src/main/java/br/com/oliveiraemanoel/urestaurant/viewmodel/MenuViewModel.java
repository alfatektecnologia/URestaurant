package br.com.oliveiraemanoel.urestaurant.viewmodel;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.adapters.MenuItemAdapter;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuViewModel extends ViewModel {

    private List<UMenu> mUMenuList = new ArrayList<>();
    private List<UMenu> mUMenuListItem = new ArrayList<>();
    private List<Restaurant> restaurantList = new ArrayList<>();
    List<Item> itemList = new ArrayList<>();
    //data that will be mutable asynchronously
    private MutableLiveData<List<UMenu>> listMutableLiveData;
    private MutableLiveData<List<Restaurant>> listMutableLiveDataRestaurant;
    private MutableLiveData<List<Item>> listMutableLiveDataMenuItem;
    private MenuItemAdapter itemAdapter;

    public static int index4ItemRecyclerList = 0;//default value

    //method to get the menu data
    public LiveData<List<UMenu>> getMenu(){
        //check if mutablelist has data
        if(listMutableLiveData==null){

            listMutableLiveData = new MutableLiveData<List<UMenu>>();
            loadMenu();
        }

        return listMutableLiveData;
    }



    //method to get the menuItem data
    public LiveData<List<Item>> getMenuItem(){
        //check if mutablelist has data
        if(listMutableLiveDataMenuItem==null){

            listMutableLiveDataMenuItem = new MutableLiveData<List<Item>>();
            //loadMenuItem();
        }

        return listMutableLiveDataMenuItem;
    }

    //method to get the restaurant data
    public LiveData<List<Restaurant>> getRestaurant(){
        //check if mutablelist has data
        if(listMutableLiveDataRestaurant==null){

            listMutableLiveDataRestaurant = new MutableLiveData<List<Restaurant>>();
            loadRestaurant();
        }

        return listMutableLiveDataRestaurant;
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

                mUMenuList = listMutableLiveData.getValue();
                Log.d("RESPONSE", "MENU_SIZE_ITEMS= " + mUMenuList.size());

               // Log.d("RESPONSE", "ITEM= " + mMenuListItem.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<UMenu>> call, Throwable t) {
             Log.d("RESPONSE_FAILURE", t.toString());
            }
        });
    }

    /*private void loadMenuItem(){

        for(int i=0;i<mMenuListItem.size();i++){

            itemList.add(i, mMenuListItem.get(i));

                  if(i==mMenuListItem.size()){
                      listMutableLiveDataMenuItem.setValue(itemList.get(0));
                  }
        }

    }*/

    //using retrofit to get restaurant data from webservice
    private void loadRestaurant(){

        Retrofit retrofit;
        retrofit = RetrofitClientInstance.getRetrofitInstance();

        GetDataService getDataService = retrofit.create(GetDataService.class);

        Call<List<Restaurant>> call = getDataService.getRestaurant();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {


                listMutableLiveDataRestaurant.setValue(response.body());


                restaurantList = listMutableLiveDataRestaurant.getValue();
                Log.d("RESPONSE", "RESTAURANT_SIZE= " + restaurantList.size());


                Log.d("RESPONSE", "NAME= " + restaurantList.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {

                Log.d("RESPONSE_FAILURE", t.toString());

            }
        });
    }

}
