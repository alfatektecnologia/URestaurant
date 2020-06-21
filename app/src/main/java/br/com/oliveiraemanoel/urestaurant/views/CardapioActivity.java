package br.com.oliveiraemanoel.urestaurant.views;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuGrupoAdapter;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuItemAdapter;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.Restaurant;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;

import br.com.oliveiraemanoel.urestaurant.viewmodel.MenuViewModel;

public class CardapioActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private static RecyclerView itemRecycler;
    MenuGrupoAdapter adapter;
    private static MenuItemAdapter itemAdapter;
    private static Context context;
    private List<UMenu> uMenuList = new ArrayList<>();
    private static List<List<Item>> groupItemList = new ArrayList<>();
    private List<List<Item>> itemList = new ArrayList<>();
    //widgets
    private TextView tv_valor_minimo;
    private TextView tv_total;
    private Button bt_closeOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        getSupportActionBar().setTitle(R.string.mainTitle);

        //widgets
        tv_total = findViewById(R.id.tvTotalBottomBar);
        tv_valor_minimo = findViewById(R.id.tvMinimoValorBottomBar);
        bt_closeOrder = findViewById(R.id.bt_close_order);


        //group recyclerView
        recyclerView = findViewById(R.id.rvMenuGrupo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        //Items recyclerView
        itemRecycler = findViewById(R.id.rvMenuItems);
        itemRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        //accessing viewmodel
        MenuViewModel menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);

        menuViewModel.getAll().observe(this, new Observer<List<UMenu>>() {

            @Override
            public void onChanged(List<UMenu> uMenus) {
                Log.d("MAIN_ACTIVITY", "MENU_SIZE= " + uMenus.size());
                uMenuList = uMenus;
                adapter = new MenuGrupoAdapter(uMenus, getApplicationContext());
                recyclerView.setAdapter(adapter);

                menuViewModel.getGroupItems(uMenus);

            }
        });

        menuViewModel.getRestaurant().observe(this, new Observer<Restaurant>() {
            @Override
            public void onChanged(Restaurant restaurant) {
                int valor = restaurant.getMinimum_order_price();
                String valorMinimo = "valor mínimo: R$ " + String.valueOf(valor);
                tv_valor_minimo.setText(valorMinimo);
            }
        });

        menuViewModel.getMenuItem(uMenuList).observe(this, new Observer<List<List<Item>>>() {
            @Override
            public void onChanged(List<List<Item>> lists) {
                groupItemList = lists;
                updateAdapter(true);

            }
        });


    }

    //this method update itemAdapter with the new list from group selected by user in
    //menugroupadapter
    public static void updateAdapter(boolean adapter) {
        if (adapter) { //setting group 0(burguers) as default to show items

            itemAdapter = new MenuItemAdapter(context,
                    groupItemList.get(MenuViewModel.getIndex().getValue()));
            itemRecycler.setAdapter(itemAdapter);
            itemAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_action_bar, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cartAction:
                startActivity(new Intent(this, CarrinhoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}