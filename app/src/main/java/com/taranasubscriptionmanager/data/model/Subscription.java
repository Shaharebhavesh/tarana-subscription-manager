package com.taranasubscriptionmanager.data.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "subscriptions",
        foreignKeys ={
                        @ForeignKey(entity = User.class,
                                            parentColumns = "id",
                                            childColumns  ="userId",
                                            onDelete = ForeignKey.CASCADE),

                        @ForeignKey(entity = Product.class,
                                    parentColumns ="id",
                                    childColumns = "productId",
                                    onDelete = ForeignKey.CASCADE)
        },
       indices ={@Index("userId"),@Index("productId")})
public class Subscription {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public int userId;
    public int productId;
    public int defaultQuantity;


}
