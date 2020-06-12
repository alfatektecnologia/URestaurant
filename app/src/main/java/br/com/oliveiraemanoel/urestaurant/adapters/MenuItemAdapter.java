package br.com.oliveiraemanoel.urestaurant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.models.Item;
import br.com.oliveiraemanoel.urestaurant.models.Menu;
public class MenuItemAdapter extends RecyclerView.Adapter<MenuItemAdapter.ViewHolder> {

    Context context;
    private List<Item> menuItemList;

    private int qty=0;
    private int total=0;

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


        holder.tvQty.setText(String.valueOf(qty));
        holder.tvPrice.setText("R$ " + String.valueOf(menuItemList.get(position).getPrice()));
        holder.tvDescription.setText(menuItemList.get(position).getDescription());
        holder.tvNome.setText(menuItemList.get(position).getName());
        holder.btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty>=0){
                    qty++;
                    holder.tvQty.setText(String.valueOf(qty));
                };
            }
        });

        holder.btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(qty>0){
                    qty--;
                    holder.tvQty.setText(String.valueOf(qty));

                }
            }
        });


        Picasso.get()

                .load(menuItemList.get(position).getImage_url())
                //.centerCrop()
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
