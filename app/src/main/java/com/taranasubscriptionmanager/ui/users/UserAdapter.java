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

    // ✅ INTERFACE (This was missing)
    public interface OnUserActionListener {
        void onEdit(User user);
        void onDeactivate(int userId);
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

        holder.binding.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(user);
            }
        });

        holder.binding.btnDeactivate.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeactivate(user.id);
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