package com.taranasubscriptionmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.taranasubscriptionmanager.data.model.Delivery;

import java.util.List;

@Dao
public interface DeliveryDao {

    @Insert
    void insert(Delivery delivery);

    // Monthly revenue
    @Query("SELECT IFNULL(SUM(totalAmount),0) FROM deliveries WHERE date BETWEEN :start AND :end")
    Double getMonthlyRevenue(long start, long end);

    // Today's total quantity
    @Query("SELECT IFNULL(SUM(quantity),0) FROM deliveries WHERE date = :today")
    Integer getTodayTotal(long today);

    // Today's revenue
    @Query("SELECT IFNULL(SUM(totalAmount),0) FROM deliveries WHERE date = :today")
    Double getTodayRevenue(long today);

    @Query("SELECT * FROM deliveries WHERE date = :today")
    LiveData<List<Delivery>> getTodayDeliveries(long today);

    @Query("SELECT COUNT(*) FROM deliveries WHERE date = :today")
    int getTodayDeliveryCount(long today);

}