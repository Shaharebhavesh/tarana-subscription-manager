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

    // product quantities
    public int tofuQty;
    public int milkQty;

    public boolean isActive = true;

    public long createdAt;

    public User(String name,
                String mobile,
                String address,
                String startDate,
                int tofuQty,
                int milkQty) {

        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.startDate = startDate;
        this.tofuQty = tofuQty;
        this.milkQty = milkQty;

        this.createdAt = System.currentTimeMillis();
    }
}