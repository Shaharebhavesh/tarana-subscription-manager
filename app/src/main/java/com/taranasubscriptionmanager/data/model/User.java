package com.taranasubscriptionmanager.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users",indices = {@Index("mobile")})
public class User  {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String name;

    public String mobile;

    public String address;

    public boolean isActive=true;

    public long createdAt;

}
