package com.taranasubscriptionmanager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.taranasubscriptionmanager.data.local.AppDatabase;
import com.taranasubscriptionmanager.data.local.UserDao;
import com.taranasubscriptionmanager.data.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;

    private final ExecutorService executorService =
            Executors.newSingleThreadExecutor();

    public UserRepository(Application application) {

        // FIX: use getDatabase() instead of getInstance()
        AppDatabase db = AppDatabase.getDatabase(application);

        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    // GET ALL USERS
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // INSERT USER
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    // UPDATE USER
    public void update(User user) {
        executorService.execute(() -> userDao.update(user));
    }

    // DELETE USER
    public void delete(User user) {
        executorService.execute(() -> userDao.delete(user));
    }

    // GET ACTIVE USERS
    public LiveData<List<User>> getActiveUsers() {
        return userDao.getActiveUsers();
    }
}