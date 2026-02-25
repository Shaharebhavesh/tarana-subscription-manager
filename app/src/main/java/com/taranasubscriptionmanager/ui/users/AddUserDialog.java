package com.taranasubscriptionmanager.ui.users;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.taranasubscriptionmanager.R;
import com.taranasubscriptionmanager.data.model.User;
import com.taranasubscriptionmanager.viewmodel.UserViewModel;

public class AddUserDialog {

    public static void show(Context context, UserViewModel viewModel) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_user, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etMobile = view.findViewById(R.id.etMobile);
        EditText etAddress = view.findViewById(R.id.etAddress);

        new AlertDialog.Builder(context)
                .setTitle("Add New Customer")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {

                    String name = etName.getText().toString().trim();
                    String mobile = etMobile.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();

                    if (name.isEmpty()) {
                        Toast.makeText(context,
                                "Name is required",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    viewModel.addUser(name, mobile, address);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Optional edit version (we’ll enhance later)
    public static void showEdit(Context context,
                                UserViewModel viewModel,
                                User user) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_user, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etMobile = view.findViewById(R.id.etMobile);
        EditText etAddress = view.findViewById(R.id.etAddress);

        etName.setText(user.name);
        etMobile.setText(user.mobile);
        etAddress.setText(user.address);

        new AlertDialog.Builder(context)
                .setTitle("Edit Customer")
                .setView(view)
                .setPositiveButton("Update", (dialog, which) -> {

                    user.name = etName.getText().toString();
                    user.mobile = etMobile.getText().toString();
                    user.address = etAddress.getText().toString();

                    viewModel.updateUser(user);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}