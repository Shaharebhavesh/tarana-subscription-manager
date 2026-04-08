package com.taranasubscriptionmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.taranasubscriptionmanager.data.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    // ALL USERS
    @Query("SELECT * FROM users ORDER BY createdAt DESC")
    LiveData<List<User>> getAllUsers();

    // ACTIVE USERS
    @Query("SELECT * FROM users WHERE isActive = 1")
    LiveData<List<User>> getActiveUsers();

    // COUNT
    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")
    LiveData<Integer> getActiveUsersCount();

    // TOTALS
    @Query("SELECT SUM(tofuQty) FROM users WHERE isActive = 1")
    LiveData<Integer> getTotalTofuRequired();

    @Query("SELECT SUM(milkQty) FROM users WHERE isActive = 1")
    LiveData<Integer> getTotalMilkRequired();

    // ✅ PAGINATION (FINAL)
    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY createdAt DESC")
    PagingSource<Integer, User> getPagedUsers();
}