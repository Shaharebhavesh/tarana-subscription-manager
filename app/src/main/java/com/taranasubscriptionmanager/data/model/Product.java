package com.taranasubscriptionmanager.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "products",indices = {@Index("name")})
public class Product {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public double pricePerUnit;

    public boolean isActive=true;
}
