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

    @Query("SELECT * FROM users WHERE isActive = 1 ORDER BY name ASC")
    LiveData<List<User>> getActiveUsers();

    @Query("UPDATE users SET isActive = 0 WHERE id = :userId")
    void deactivateUser(int userId);

    @Query("SELECT * FROM users WHERE id = :userId")
    User getUserById(int userId);
    @Query("SELECT COUNT(*) FROM users WHERE isActive = 1")

    Integer getActiveUsersCount();
}
