package com.taranasubscriptionmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
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
    void update (User user);

    @Query("select * from users where isActive = 1 ")
    LiveData<List<User>> getActiveUsers();

    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")


    Integer getActiveUsersCount();
}
