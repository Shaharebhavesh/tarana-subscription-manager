package com.taranasubscriptionmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.taranasubscriptionmanager.data.model.User;

import java.util.List;

//@Dao
//public interface UserDao {
//
//    @Insert
//    void insert(User user);
//
//    @Update
//    void update(User user);
//
//    @Delete
//    void delete(User user);
//
//    @Query("SELECT * FROM users ORDER BY name ASC")
//    LiveData<List<User>> getAllUsers();
//
//    @Query("SELECT * FROM users WHERE isActive = 1")
//    LiveData<List<User>> getActiveUsers();
//}
@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM users ORDER BY createdAt DESC")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM users WHERE isActive = 1")
    LiveData<List<User>> getActiveUsers();

    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")
    LiveData<Integer> getActiveUsersCount();

    @Query("SELECT SUM(tofuQty) FROM users WHERE isActive = 1")
    LiveData<Integer> getTotalTofuRequired();

    @Query("SELECT SUM(milkQty) FROM users WHERE isActive = 1")
    LiveData<Integer> getTotalMilkRequired();



}