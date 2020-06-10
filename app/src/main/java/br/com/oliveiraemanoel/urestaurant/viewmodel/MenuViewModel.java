package br.com.oliveiraemanoel.urestaurant.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.models.Menu;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MenuViewModel extends ViewModel {

    private List<Menu> mMenuList = new ArrayList<>();
    private List<Menu.Item> mMenuListItem = new ArrayList<>();
    private List<Restaurant> restaurantList = new ArrayList<>();

    //data that will be mutable asynchronously
    private MutableLiveData<List<Menu>> listMutableLiveData;
    private MutableLiveData<List<Restaurant>> listMutableLiveDataRestaurant;

    //method to get the menu data
    public LiveData<List<Menu>> getMenu(){
        //check if mutablelist has data
        if(listMutableLiveData==null){

            listMutableLiveData = new MutableLiveData<List<Menu>>();
            loadMenu();
        }

        return listMutableLiveData;
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

        Call<List<Menu>> call = getDataService.getMenu();
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
//
                // generateDataList(response.body());
               listMutableLiveData.setValue(response.body());
                // mMenuList.get(0).getId();
               // Log.d("RESPONSE", "MENU_SIZE= Grupos  " + mMenuList.size());//qdade de grupos
                mMenuListItem = listMutableLiveData.getValue().get(0).getItems();
                Log.d("RESPONSE", "MENU_SIZE_ITEMS= " + mMenuListItem.size());

               // Log.d("RESPONSE", "ID= " + mMenuList.get(0).getId());
               // Log.d("RESPONSE", "NAME= " + mMenuList.get(0).getName());
                Log.d("RESPONSE", "ITEM= " + mMenuListItem.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
//                progressDoalog.dismiss();
                //Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //using retrofit to get restaurant data from webservice
    private void loadRestaurant(){

        Retrofit retrofit;
        retrofit = RetrofitClientInstance.getRetrofitInstance();

        GetDataService getDataService = retrofit.create(GetDataService.class);

        Call<List<Restaurant>> call = getDataService.getRestaurant();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
//
                // generateDataList(response.body());
                listMutableLiveDataRestaurant.setValue(response.body());
                // mMenuList.get(0).getId();
                // Log.d("RESPONSE", "MENU_SIZE= Grupos  " + mMenuList.size());//qdade de grupos
                restaurantList = listMutableLiveDataRestaurant.getValue();
                Log.d("RESPONSE", "RESTAURANT_SIZE= " + restaurantList.size());

                // Log.d("RESPONSE", "ID= " + mMenuList.get(0).getId());
                // Log.d("RESPONSE", "NAME= " + mMenuList.get(0).getName());
                Log.d("RESPONSE", "NAME= " + restaurantList.get(0).getName());
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
//                progressDoalog.dismiss();
                Log.d("RESPONSE_FAILURE", t.toString());
                //Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
