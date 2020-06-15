package br.com.oliveiraemanoel.urestaurant.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuGrupoAdapter;
import br.com.oliveiraemanoel.urestaurant.adapters.MenuItemAdapter;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.util.CountDrawable;
import br.com.oliveiraemanoel.urestaurant.viewmodel.MenuViewModel;

public class CardapioActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private static RecyclerView itemRecycler;
    MenuGrupoAdapter adapter;
    private static MenuItemAdapter itemAdapter;
    private static Context context;
    private static List<List<Item>> groupItemList = new ArrayList<>();
    private List<List<Item>> itemList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        getSupportActionBar().setTitle(R.string.mainTitle);


        recyclerView = findViewById(R.id.rvMenuGrupo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        itemRecycler = findViewById(R.id.rvMenuItems);

        itemRecycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        //accessing viewmodel
        MenuViewModel menuViewModel = ViewModelProviders.of(this).get(MenuViewModel.class);

        menuViewModel.getMenu().observe(this, new Observer<List<UMenu>>() {

            @Override
            public void onChanged(List<UMenu> UMenus) {
                Log.d("MAIN_ACTIVITY","MENU_SIZE= " + UMenus.size());

               adapter = new MenuGrupoAdapter(UMenus,getApplicationContext());
               recyclerView.setAdapter(adapter);

               if(UMenus.size()>0) {
                   //adding items of each group to list of items
                   for (int i = 0; i < UMenus.size(); i++) {
                       itemList.add(i, UMenus.get(i).getItems());
                   }

                   //creating a list of items by group
                   int x = -1;
                   for (List<Item> listList : itemList) {
                       x++;
                       groupItemList.add(x, listList);

                   }
               }

                if(groupItemList.size()>0){
                    updateAdapter(true);
                }
            }
        });

    }
    //this method update itemAdapter with the new list from group selected by user in
    //menugroupadapter
    public static void updateAdapter(boolean adapter){
        if(adapter)
        { //setting group 0(burguers) as default to show items

                itemAdapter = new MenuItemAdapter(context,
                        groupItemList.get(MenuViewModel.index4ItemRecyclerList));
                itemRecycler.setAdapter(itemAdapter);
                itemAdapter.notifyDataSetChanged();

            }

    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_action_bar, menu);
      // setCount(this, "9");
        return true;

    }

    public void setCount(Context context, String count) {
        Menu menu = findViewById(R.id.cartAction);
        MenuItem menuItem = menu.findItem(R.id.cartIcom);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();

        CountDrawable badge;

        // Reuse drawable if possible
        Drawable reuse = icon.findDrawableByLayerId(R.id.cartCount);
        if (reuse != null && reuse instanceof CountDrawable) {
            badge = (CountDrawable) reuse;
        } else {
            badge = new CountDrawable(context);
        }

        badge.setCount(count);
        icon.mutate();
        icon.setDrawableByLayerId(R.id.cartCount, badge);
    }


}