package com.taranasubscriptionmanager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.taranasubscriptionmanager.data.local.AppDatabase;
import com.taranasubscriptionmanager.data.model.User;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class UserRepository {
    private final AppDatabase db;
    private final ExecutorService executor;

    public UserRepository(Application application){
        db = AppDatabase.getDatabase(application);
        executor = Executors.newSingleThreadExecutor();
    }
    public LiveData<List<User>>getActiveUsers(){
        return db.userDao().getActiveUsers();
    }

    public void insert(User user){
        executor.execute(()->db.userDao().insert(user));
    }

    public void update(User user){
        executor.execute(()->db.userDao().update(user));
    }

    public void deactivate(int userId){
        executor.execute(()->db.userDao().deactivateUser(userId));
    }

    public void getUser(int userId, Consumer<User>callback){
        executor.execute(()->{
            User user=db.userDao().getUserById(userId);
            callback.accept(user);
        });
    }




}
