package br.com.oliveiraemanoel.urestaurant.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.TempCart;
import br.com.oliveiraemanoel.urestaurant.repositories.RestaurantRoomDBRepository;


public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    Context context;
    private List<Item> menuItemList;
    private List<Integer> qty = new ArrayList<>();
    private List<Integer> qtyMatriz = new ArrayList<>();
    private double total=0;
    private int x=0;
    private int selected=0;
    private int lastSelected=0;
    private RestaurantRoomDBRepository restaurantRoomDBRepository;


    Application application;
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
        //getting instance of database repository
        restaurantRoomDBRepository = new RestaurantRoomDBRepository(application);

        //populating list with 0
        //here we will have a list with the size of getItemCount, populated with 0
        qty.add(position,0);
        qtyMatriz.add(0);//used to populate qty after cleared

        //formatting the price to locale format used on device
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

                //clear list when other item is selected
                if(selected!=lastSelected){
                    qty.clear();
                    qty.addAll(qtyMatriz);
                }
                //get the actual qty from the list
                x = qty.get(position);
                if(x>=0){
                    qty.add(position,x+1);

                    //adding 1 to x because x starts with value equal 0
                    total = (x+1) * menuItemList.get(position).getPrice();
                    //preparing data to be saved in database
                    cart = new TempCart(
                            menuItemList.get(position).getId(),
                            menuItemList.get(position).getName(),
                            x+1,
                            menuItemList.get(position).getPrice(),
                            total,
                            position

                    );
                    //saving data to room database
                    restaurantRoomDBRepository.insert(cart);
                    //updating list
                    cartList.add(position,cart);

                    //updating textview
                    holder.tvQty.setText(String.valueOf(qty.get(position)));
                };

            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save the position of the last selected item
                lastSelected = selected;
                //Save the position of the current selected item
                selected = position;

                    qty.clear();
                    qty.addAll(qtyMatriz);
                    restaurantRoomDBRepository.delete(position,menuItemList.get(position).getId());
                   // Log.d("ON_CLICK--","MATRIZ<<" + cartList.toString() + "  X = "+ x + " "+qty.toString());
                    holder.tvQty.setText("0");

            }
        });

        //getting image
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
        Button btDelete,btPlus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ivMenuItem);
            tvDescription = itemView.findViewById(R.id.tvMenuItemDescription);
            tvNome = itemView.findViewById(R.id.tvMenuItemName);
            tvPrice = itemView.findViewById(R.id.tvMenuItemPrice);
            tvQty = itemView.findViewById(R.id.tvMenuitemQty);
            btDelete = itemView.findViewById(R.id.btMenuItemDelete);
            btPlus = itemView.findViewById(R.id.btMenuItemPlus);


        }


    }


}
