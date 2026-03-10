package com.taranasubscriptionmanager.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "deliveries")
public class Delivery {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public int quantity;
    public double totalAmount;
    public long date;

}