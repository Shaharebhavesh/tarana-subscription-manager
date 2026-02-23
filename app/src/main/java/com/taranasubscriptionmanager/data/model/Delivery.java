package com.taranasubscriptionmanager.data.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "deliveries",indices = {@Index("userId"),
                                            @Index("productId"),
                                            @Index("date")})
public class Delivery {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public int productId;
    public long date;
    public int quantity;
    public double totalAmount;
}
