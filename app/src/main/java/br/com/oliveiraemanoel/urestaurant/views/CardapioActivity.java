package br.com.oliveiraemanoel.urestaurant.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuGrupoAdapter;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuItemAdapter;
import br.com.oliveiraemanoel.urestaurant.models.Menu;
import br.com.oliveiraemanoel.urestaurant.retrofit.GetDataService;
import br.com.oliveiraemanoel.urestaurant.retrofit.RetrofitClientInstance;
import br.com.oliveiraemanoel.urestaurant.viewmodel.MenuViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CardapioActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView itemRecycler;
    MenuGrupoAdapter adapter;
    MenuItemAdapter itemAdapter;
    private List<Menu> groupList = new ArrayList<>();
    private List<Menu.Item> itemList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.mainTitle);

        recyclerView = findViewById(R.id.rvMenuGrupo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        itemRecycler = findViewById(R.id.rvMenuItems);
        itemRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        MenuViewModel menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);

        menuViewModel.getMenu().observe(this, new Observer<List<Menu>>() {
            @Override
            public void onChanged(List<Menu> menus) {
                Log.d("MAIN_ACTIVY","MENU_SIZE= " +menus.size());

               adapter = new MenuGrupoAdapter(menus,getApplicationContext());
               recyclerView.setAdapter(adapter);

               itemAdapter = new MenuItemAdapter(getApplicationContext(),menus.get(0).getItems());
               itemRecycler.setAdapter(itemAdapter);

            }
        });



    }
}