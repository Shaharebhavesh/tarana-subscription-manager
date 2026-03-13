package com.taranasubscriptionmanager.ui.delivery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taranasubscriptionmanager.R;
import com.taranasubscriptionmanager.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.ViewHolder>{

    private List<User> users = new ArrayList<>();

    public void setUsers(List<User> list){
        users = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position){

        User user = users.get(position);

        holder.name.setText(user.name);

        holder.qty.setText(
                "Tofu: "+user.tofuQty+"   Milk: "+user.milkQty
        );
    }

    @Override
    public int getItemCount(){
        return users.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,qty;

        ViewHolder(View v){
            super(v);
            name = v.findViewById(R.id.tvName);
            qty = v.findViewById(R.id.tvQty);
        }
    }
}