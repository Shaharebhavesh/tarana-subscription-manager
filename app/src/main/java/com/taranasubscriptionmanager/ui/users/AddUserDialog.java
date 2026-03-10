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

    // ADD USER
    public static void show(Context context, UserViewModel viewModel) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_user, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etMobile = view.findViewById(R.id.etMobile);
        EditText etAddress = view.findViewById(R.id.etAddress);
        EditText etStartDate = view.findViewById(R.id.etStartDate);
        EditText etTofuQty = view.findViewById(R.id.etTofuQty);
        EditText etMilkQty = view.findViewById(R.id.etMilkQty);

        new AlertDialog.Builder(context)
                .setTitle("Add New Customer")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {

                    String name = etName.getText().toString().trim();
                    String mobile = etMobile.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();
                    String startDate = etStartDate.getText().toString().trim();

                    int tofuQty = 0;
                    int milkQty = 0;

                    if (!etTofuQty.getText().toString().isEmpty()) {
                        tofuQty = Integer.parseInt(etTofuQty.getText().toString());
                    }

                    if (!etMilkQty.getText().toString().isEmpty()) {
                        milkQty = Integer.parseInt(etMilkQty.getText().toString());
                    }

                    viewModel.addUser(
                            name,
                            mobile,
                            address,
                            startDate,
                            tofuQty,
                            milkQty
                    );

                    Toast.makeText(context,
                            "User Added",
                            Toast.LENGTH_SHORT).show();

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // EDIT USER
    public static void showEdit(Context context,
                                UserViewModel viewModel,
                                User user) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_user, null);

        EditText etName = view.findViewById(R.id.etName);
        EditText etMobile = view.findViewById(R.id.etMobile);
        EditText etAddress = view.findViewById(R.id.etAddress);
        EditText etStartDate = view.findViewById(R.id.etStartDate);
        EditText etTofuQty = view.findViewById(R.id.etTofuQty);
        EditText etMilkQty = view.findViewById(R.id.etMilkQty);

        etName.setText(user.name);
        etMobile.setText(user.mobile);
        etAddress.setText(user.address);
        etStartDate.setText(user.startDate);
        etTofuQty.setText(String.valueOf(user.tofuQty));
        etMilkQty.setText(String.valueOf(user.milkQty));

        new AlertDialog.Builder(context)
                .setTitle("Edit Customer")
                .setView(view)
                .setPositiveButton("Update", (dialog, which) -> {

                    user.name = etName.getText().toString();
                    user.mobile = etMobile.getText().toString();
                    user.address = etAddress.getText().toString();
                    user.startDate = etStartDate.getText().toString();

                    int tofuQty = 0;
                    int milkQty = 0;

                    if (!etTofuQty.getText().toString().isEmpty()) {
                        tofuQty = Integer.parseInt(etTofuQty.getText().toString());
                    }

                    if (!etMilkQty.getText().toString().isEmpty()) {
                        milkQty = Integer.parseInt(etMilkQty.getText().toString());
                    }

                    user.tofuQty = tofuQty;
                    user.milkQty = milkQty;

                    viewModel.updateUser(user);

                    Toast.makeText(context,
                            "User Updated",
                            Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}