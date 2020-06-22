package br.com.oliveiraemanoel.urestaurant.adapters;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.models.Cart;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.TempCart;
import br.com.oliveiraemanoel.urestaurant.repositories.daos.URestaurantDAO;
import br.com.oliveiraemanoel.urestaurant.repositories.databases.URestaurantRoomDB;


public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    Context context;
    private List<Item> menuItemList;
    private List<Integer> qty = new ArrayList<>();
    private List<Integer> qtyMatriz = new ArrayList<>();
    private double total=0;
    private int x=0;
    private int selected=0;
    private int lastSelected=0;
    private URestaurantRoomDB uRestaurantRoomDB;
    private TempCart cart;
    private List<TempCart> cartList = new ArrayList<>();


    public MenuItemAdapter(Context context, List<Item> menuItemList) {
        this.context = context;
        this.menuItemList = menuItemList;
    }

    @NonNull
    @Override
    public MenuItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.menu_item,parent,false);
        ViewHolder viewHolder= new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemAdapter.ViewHolder holder, int position)  {
        uRestaurantRoomDB = new URestaurantRoomDB() {
            @Override
            public URestaurantDAO uRestaurantDAO() {

                return null;
            }

            @NonNull
            @Override
            protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
                return null;
            }

            @NonNull
            @Override
            protected InvalidationTracker createInvalidationTracker() {
                return null;
            }

            @Override
            public void clearAllTables() {

            }
        };
        qty.add(position,0);
        qtyMatriz.add(0);//used to populate qty after cleared
        NumberFormat myCurrencyFormatted = NumberFormat.getCurrencyInstance();
        String price = myCurrencyFormatted.format(menuItemList.get(position).getPrice());

        holder.tvQty.setText(String.valueOf(qty.get(position)));
        holder.tvPrice.setText( price );
        holder.tvDescription.setText(menuItemList.get(position).getDescription());
        holder.tvNome.setText(menuItemList.get(position).getName());
        holder.btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the position of the last selected item
                lastSelected = selected;
                //Save the position of the current selected item
                selected = position;

                if(selected!=lastSelected){
                    qty.clear();
                    qty.addAll(qtyMatriz);
                }
                x = qty.get(position);
                if(x>=0){
                    qty.add(position,x+1);
                    total = (x+1) * menuItemList.get(position).getPrice();
                    cart = new TempCart(
                            menuItemList.get(position).getId(),
                            menuItemList.get(position).getName(),
                            x+1,
                            menuItemList.get(position).getPrice(),
                            total

                    );
                    uRestaurantRoomDB.uRestaurantDAO().insert(cart);
                    cartList.add(position,cart);
                    Log.d("ON_CLICK++","MATRIZ<<"+cartList.toString() + "  X = "+ x + " "+qty.toString());
                    holder.tvQty.setText(String.valueOf(qty.get(position)));
                };

            }
        });

        holder.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the position of the last selected item
                lastSelected = selected;
                //Save the position of the current selected item
                selected = position;

                if(selected!=lastSelected){
                    qty.clear();
                    qty.addAll(qtyMatriz);
                }
                x = qty.get(position);
                if(x>0){

                    Log.d("ON_CLICK--","X = "+ x);

                    qty.add(position,x-1);

                    total = x * menuItemList.get(position).getPrice();
                    cart = new TempCart(
                            menuItemList.get(position).getId(),
                            menuItemList.get(position).getName(),
                            x,
                            menuItemList.get(position).getPrice(),
                            total

                    );
                    cartList.add(position,cart);
                    Log.d("ON_CLICK++","MATRIZ<<"+cartList.toString() + "  X = "+ x + " "+qty.toString());
                    holder.tvQty.setText(String.valueOf(qty.get(position)));
                };
            }
        });


        Picasso.get()

                .load(menuItemList.get(position).getImage_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return menuItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvNome,tvQty,tvDescription,tvPrice;
        Button btMinus,btPlus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivMenuItem);
            tvDescription = itemView.findViewById(R.id.tvMenuItemDescription);
            tvNome = itemView.findViewById(R.id.tvMenuItemName);
            tvPrice = itemView.findViewById(R.id.tvMenuItemPrice);
            tvQty = itemView.findViewById(R.id.tvMenuitemQty);
            btMinus = itemView.findViewById(R.id.btMenuItemMinus);
            btPlus = itemView.findViewById(R.id.btMenuItemPlus);


        }


    }


}
