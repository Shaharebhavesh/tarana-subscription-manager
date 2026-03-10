package com.taranasubscriptionmanager.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.taranasubscriptionmanager.data.model.Delivery;

@Dao
public interface DeliveryDao {

    @Insert
    void insert(Delivery delivery);

    // Monthly revenue
    @Query("SELECT SUM(totalAmount) FROM deliveries WHERE date BETWEEN :start AND :end")
    Double getMonthlyRevenue(long start, long end);

    // Today's total quantity
    @Query("SELECT SUM(quantity) FROM deliveries WHERE date = :today")
    Integer getTodayTotal(long today);

    // Today's revenue
    @Query("SELECT SUM(totalAmount) FROM deliveries WHERE date = :today")
    Double getTodayRevenue(long today);
}