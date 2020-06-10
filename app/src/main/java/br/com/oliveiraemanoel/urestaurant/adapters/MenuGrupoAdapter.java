package br.com.oliveiraemanoel.urestaurant.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.oliveiraemanoel.urestaurant.R;
import br.com.oliveiraemanoel.urestaurant.models.Menu;

public class MenuGrupoAdapter extends RecyclerView.Adapter<MenuGrupoAdapter.ViewHolder> {

    private List<Menu> groupList;
    private Context context;
    private int selected=0;

    public MenuGrupoAdapter(List<Menu> groupList, Context context) {
        this.groupList = groupList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.menu_group,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvGroupName.setText(groupList.get(position).getName());
       /* holder.tvGroupName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    selected=position;
                Log.d("POSITION","seleted" + position);
                Log.d("POSITION","OldPosition" +  holder.getAdapterPosition());
               // holder.tvGroupName.setTextColor(0xff0099cc);
                if (holder.divider.getVisibility()==View.VISIBLE){
                    holder.divider.setVisibility(View.INVISIBLE);
                    //holder.getOldPosition();
                    notifyDataSetChanged();
                }else{
                    holder.divider.setVisibility(View.VISIBLE);
                }

                //setar qual item do menu deve ser exibido

            }
        });*/

        holder.tvGroupName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    holder.divider.setVisibility(View.VISIBLE);
                }
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
