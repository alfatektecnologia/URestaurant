package br.com.oliveiraemanoel.urestaurant.adapters;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.models.UMenu;
import br.com.oliveiraemanoel.urestaurant.viewmodel.MenuViewModel;
import br.com.oliveiraemanoel.urestaurant.views.CardapioActivity;

public class MenuGrupoAdapter extends RecyclerView.Adapter<MenuGrupoAdapter.ViewHolder> {

    private List<UMenu> groupList;
    private Context context;
    private int selected=0;
    private int lastSelected=0;

    public MenuGrupoAdapter(List<UMenu> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.menu_group,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvGroupName.setText(groupList.get(position).getName());
        if ((position == selected)) {
            holder.divider.setVisibility(View.VISIBLE);
        } else {
            holder.divider.setVisibility(View.INVISIBLE);
        }
        holder.tvGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update item adapter==>
                MenuViewModel.index4ItemRecyclerList=position;
                MenuViewModel.getIndex();
                CardapioActivity.updateAdapter(true);

                //Save the position of the last selected item
                lastSelected = selected;
                //Save the position of the current selected item
                selected = position;

                //This update the last item selected
                notifyItemChanged(lastSelected);

                //This update the item selected
                notifyItemChanged(selected);

            }
        });

    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGroupName;
        View  divider;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGroupName = itemView.findViewById(R.id.tvGroupName);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}
