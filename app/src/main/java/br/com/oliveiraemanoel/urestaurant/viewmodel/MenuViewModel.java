package br.com.oliveiraemanoel.urestaurant.viewmodel;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.TempCart;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.repositories.RestaurantRoomDBRepository;
import br.com.oliveiraemanoel.urestaurant.repositories.RestaurantWebRepository;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MenuViewModel extends AndroidViewModel {

    public static String deviceLocale = Locale.getDefault().getCountry();

    //restaurant
    private Restaurant restaurantList = new Restaurant();
    private MutableLiveData<Restaurant> listMutableLiveDataRestaurant;
    private LiveData<Restaurant> restaurantLiveData;
    private final LiveData<Restaurant> restau;

    //menu
    private MutableLiveData<List<List<Item>>> listMutableLiveDataMenuItem;
    private static List<List<Item>> groupItemList = new ArrayList<>();
    private List<List<Item>> itemList = new ArrayList<>();
    private LiveData<List<UMenu>> mAllMenu;
    private final LiveData<List<UMenu>>  retroObservable;

    //item recyclerView
    public static int index4ItemRecyclerList = 0;//default value
    private static MutableLiveData<Integer> index4ListItemMutable;

    //order
    private MutableLiveData<Integer> integerMutableLiveData;

    //TempCart
    private LiveData<List<TempCart>> tempCartLiveData;


    //repository
    private RestaurantRoomDBRepository restaurantRoomDBRepository;
    private RestaurantWebRepository restaurantWebRepository;


    public MenuViewModel(@NonNull Application application) {
        super(application);
        integerMutableLiveData = new MutableLiveData<Integer>();
        restaurantRoomDBRepository = new RestaurantRoomDBRepository(application);
        restaurantWebRepository = new RestaurantWebRepository(application);
        retroObservable = restaurantWebRepository.getMenu();
        restau = restaurantWebRepository.getRestaurant();
        mAllMenu =  restaurantRoomDBRepository.getAll();
        restaurantLiveData = restaurantRoomDBRepository.getAllRestaurants();
        tempCartLiveData = restaurantRoomDBRepository.getAllCart();
    }

    //getting all data from menu
    public LiveData<List<UMenu>> getAll() {
        return mAllMenu;
    }

    //getting all data from tempCart
    public LiveData<List<TempCart>> getAllCart(){return  tempCartLiveData;}

    //just testing...
    public LiveData<Integer> setInteger(Integer i){
        int valorAtual=0;
        if(integerMutableLiveData!=null){
           // valorAtual = integerMutableLiveData.getValue();
        }

        integerMutableLiveData.setValue(i+valorAtual);

        return integerMutableLiveData;
    }


    //method to get the menuItem data
    public LiveData<List<List<Item>>> getMenuItem(List<UMenu> uMenus){
        //check if mutablelist has data
        if(listMutableLiveDataMenuItem==null){

            listMutableLiveDataMenuItem = new MutableLiveData<List<List<Item>>>();
            getGroupItems(uMenus);
        }

        return listMutableLiveDataMenuItem;
    }

    //method to get the restaurant data
    public LiveData<Restaurant> getRestaurant(){
        //check if mutablelist has data
        if(listMutableLiveDataRestaurant==null){

            listMutableLiveDataRestaurant = new MutableLiveData<Restaurant>();
            loadRestaurant();
        }

        return listMutableLiveDataRestaurant;
    }

    //getting group
    public void getGroupItems(List<UMenu> uMenus){
        if(uMenus.size()>0) {
            //adding items of each group to list of items
            for (int i = 0; i < uMenus.size(); i++) {
                itemList.add(i, uMenus.get(i).getItems());
            }

            //creating a list of items by group
            int x = -1;
            for (List<Item> listList : itemList) {
                x++;
                groupItemList.add(x, listList);

            }
        }

        if(groupItemList.size()>0){
           listMutableLiveDataMenuItem.setValue(groupItemList);
        }
    }

    public static MutableLiveData<Integer> getIndex(){

        if(index4ListItemMutable==null){
            index4ListItemMutable = new MutableLiveData<>();

        }
        setIndex4ListItem();
        Log.d("GET_INDEX....","INDEX = "+ index4ListItemMutable.getValue());
        return index4ListItemMutable;
    }

    private static void setIndex4ListItem(){

        index4ListItemMutable.setValue(index4ItemRecyclerList);
        Log.d("SET_INDEX....","INDEX_GET = "+ index4ListItemMutable.getValue());
    }


    //using retrofit to get restaurant data from webservice
    private void loadRestaurant(){

        Retrofit retrofit;
        retrofit = RetrofitClientInstance.getRetrofitInstance();

        GetDataService getDataService = retrofit.create(GetDataService.class);

        Call<Restaurant> call = getDataService.getRestaurant();
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {

                listMutableLiveDataRestaurant.setValue(response.body());
                restaurantList = listMutableLiveDataRestaurant.getValue();

                Log.d("RESPONSE", "NAME= " + restaurantList.getName());
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

                Log.d("RESPONSE_FAILURE", t.toString());

            }
        });
    }

}
