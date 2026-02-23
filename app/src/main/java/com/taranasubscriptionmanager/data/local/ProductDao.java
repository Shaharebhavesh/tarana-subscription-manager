package com.taranasubscriptionmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.taranasubscriptionmanager.data.model.Product;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert
    void insert(Product product);

    @Query("select * from products where isActive= 1")
    LiveData<List<Product>>getActiveProducts();
}
