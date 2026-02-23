package com.taranasubscriptionmanager.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.taranasubscriptionmanager.data.model.Delivery;

@Dao
public interface DeliveryDao {

    @Insert
    void insert(Delivery delivery);

    @Query("select sum(totalAmount)from deliveries where date between :start and :end")
    double getMonthlyRevenue(long start, long end);

    @Query("select sum(quantity) from deliveries where date =:today")
    int getTodayTotal(long today);

    @Query("SELECT SUM(totalAmount) FROM deliveries WHERE date = :today")


    Double getTodayRevenue(long today);
}
