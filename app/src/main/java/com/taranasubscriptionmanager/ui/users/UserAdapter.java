package com.taranasubscriptionmanager.ui.users;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taranasubscriptionmanager.data.model.User;
import com.taranasubscriptionmanager.databinding.ItemUserBinding;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList = new ArrayList<>();
    private OnUserActionListener listener;

    public interface OnUserActionListener {

        void onEdit(User user);

        void onDeactivate(User user);

        // quantity update callback
        void onQuantityChanged(User user);
    }

    public void setListener(OnUserActionListener listener) {
        this.listener = listener;
    }

    public void submitList(List<User> users) {
        userList = users;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemUserBinding binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        User user = userList.get(position);

        holder.binding.tvName.setText(user.name);
        holder.binding.tvMobile.setText(user.mobile);
        holder.binding.tvAddress.setText(user.address);

        // show quantities
        holder.binding.tvTofuQty.setText(String.valueOf(user.tofuQty));
        holder.binding.tvMilkQty.setText(String.valueOf(user.milkQty));

        // TOFU +
        holder.binding.btnTofuPlus.setOnClickListener(v -> {

            user.tofuQty++;

            holder.binding.tvTofuQty.setText(String.valueOf(user.tofuQty));

            if (listener != null) {
                listener.onQuantityChanged(user);
            }
        });

        // TOFU -
        holder.binding.btnTofuMinus.setOnClickListener(v -> {

            if (user.tofuQty > 0) {
                user.tofuQty--;

                holder.binding.tvTofuQty.setText(String.valueOf(user.tofuQty));

                if (listener != null) {
                    listener.onQuantityChanged(user);
                }
            }
        });

        // MILK +
        holder.binding.btnMilkPlus.setOnClickListener(v -> {

            user.milkQty++;

            holder.binding.tvMilkQty.setText(String.valueOf(user.milkQty));

            if (listener != null) {
                listener.onQuantityChanged(user);
            }
        });

        // MILK -
        holder.binding.btnMilkMinus.setOnClickListener(v -> {

            if (user.milkQty > 0) {
                user.milkQty--;

                holder.binding.tvMilkQty.setText(String.valueOf(user.milkQty));

                if (listener != null) {
                    listener.onQuantityChanged(user);
                }
            }
        });

        // EDIT
        holder.binding.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(user);
            }
        });

        // DEACTIVATE
        holder.binding.btnDeactivate.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeactivate(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        ItemUserBinding binding;

        public UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}