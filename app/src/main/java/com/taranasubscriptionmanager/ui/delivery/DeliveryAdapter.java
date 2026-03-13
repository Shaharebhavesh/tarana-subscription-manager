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

public class DeliveryAdapter extends RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder> {

    private List<User> userList = new ArrayList<>();


    public void setUsers(List<User> users) {
        this.userList = users;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_delivery, parent, false);

        return new DeliveryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder holder, int position) {

        User user = userList.get(position);

        holder.tvCustomerName.setText(user.name);

        holder.tvTofuQty.setText("Tofu: " + user.tofuQty);

        holder.tvMilkQty.setText("Milk: " + user.milkQty);
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class DeliveryViewHolder extends RecyclerView.ViewHolder {

        TextView tvCustomerName;
        TextView tvTofuQty;
        TextView tvMilkQty;

        public DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCustomerName = itemView.findViewById(R.id.tvCustomerName);
            tvTofuQty = itemView.findViewById(R.id.tvTofuQty);
            tvMilkQty = itemView.findViewById(R.id.tvMilkQty);
        }
    }
}