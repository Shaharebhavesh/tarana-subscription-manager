package com.taranasubscriptionmanager.ui.users;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.taranasubscriptionmanager.data.model.User;
import com.taranasubscriptionmanager.databinding.FragmentUserBinding;
import com.taranasubscriptionmanager.viewmodel.UserViewModel;

public class UserFragment extends Fragment {

    private FragmentUserBinding binding;
    private UserViewModel viewModel;
    private UserAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);

        setupRecycler();     // 👈 CALL HERE
        observeUsers();      // 👈 AND HERE

        binding.btnAddUser.setOnClickListener(v ->
                AddUserDialog.show(requireContext(), viewModel)
        );

        return binding.getRoot();
    }

    // 🔥 ADD THIS METHOD HERE
    private void setupRecycler() {

        adapter = new UserAdapter();

        binding.recyclerUsers.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        binding.recyclerUsers.setAdapter(adapter);

        adapter.setListener(new UserAdapter.OnUserActionListener() {
            @Override
            public void onEdit(User user) {
                AddUserDialog.showEdit(requireContext(), viewModel, user);
            }

            @Override
            public void onDeactivate(int userId) {
                viewModel.deactivateUser(userId);
            }
        });
    }

    private void observeUsers() {
        viewModel.getActiveUsers().observe(getViewLifecycleOwner(),
                users -> adapter.submitList(users));
    }
}