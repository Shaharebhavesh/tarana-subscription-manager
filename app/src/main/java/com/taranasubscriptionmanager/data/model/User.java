package com.taranasubscriptionmanager.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String mobile;
    public String address;
    public String startDate;

    public String product;
    public int quantity;

    public boolean isActive = true;

    // NEW FIELD
    public long createdAt;

    public User(String name,
                String mobile,
                String address,
                String startDate,
                String product,
                int quantity) {

        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.startDate = startDate;
        this.product = product;
        this.quantity = quantity;

        // Save creation time
        this.createdAt = System.currentTimeMillis();
    }
}