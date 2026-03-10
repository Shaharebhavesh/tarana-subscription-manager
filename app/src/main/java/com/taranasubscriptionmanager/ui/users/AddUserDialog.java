package com.taranasubscriptionmanager.ui.users;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
        EditText etQuantity = view.findViewById(R.id.etQuantity);

        Spinner spProduct = view.findViewById(R.id.spProduct);

        String[] products = {"Tofu", "Soy Milk"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                products
        );

        spProduct.setAdapter(adapter);

        new AlertDialog.Builder(context)
                .setTitle("Add New Customer")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {

                    String name = etName.getText().toString().trim();
                    String mobile = etMobile.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();
                    String startDate = etStartDate.getText().toString().trim();
                    String product = spProduct.getSelectedItem().toString();

                    int quantity = 0;

                    if (!etQuantity.getText().toString().isEmpty()) {
                        quantity = Integer.parseInt(etQuantity.getText().toString());
                    }

                    viewModel.addUser(
                            name,
                            mobile,
                            address,
                            startDate,
                            product,
                            quantity
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
        EditText etQuantity = view.findViewById(R.id.etQuantity);

        Spinner spProduct = view.findViewById(R.id.spProduct);

        String[] products = {"Tofu", "Soy Milk"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_dropdown_item,
                products
        );

        spProduct.setAdapter(adapter);

        // Fill existing values
        etName.setText(user.name);
        etMobile.setText(user.mobile);
        etAddress.setText(user.address);
        etStartDate.setText(user.startDate);
        etQuantity.setText(String.valueOf(user.quantity));

        new AlertDialog.Builder(context)
                .setTitle("Edit Customer")
                .setView(view)
                .setPositiveButton("Update", (dialog, which) -> {

                    user.name = etName.getText().toString();
                    user.mobile = etMobile.getText().toString();
                    user.address = etAddress.getText().toString();
                    user.startDate = etStartDate.getText().toString();
                    user.product = spProduct.getSelectedItem().toString();
//                    user.quantity = Integer.parseInt(etQuantity.getText().toString());
                    int quantity = 0;

                    if (!etQuantity.getText().toString().isEmpty()) {
                        quantity = Integer.parseInt(etQuantity.getText().toString());
                    }

                    user.quantity = quantity;
                    viewModel.updateUser(user);

                    Toast.makeText(context,
                            "User Updated",
                            Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}