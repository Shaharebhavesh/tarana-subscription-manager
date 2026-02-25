package com.taranasubscriptionmanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.taranasubscriptionmanager.data.model.User;
import com.taranasubscriptionmanager.data.repository.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository repository;
    private final LiveData<List<User>>activeUsers;

    public UserViewModel(@NonNull Application application){
        super(application);
        repository = new UserRepository(application);
        activeUsers = repository.getActiveUsers();
    }
    public LiveData<List<User>>getActiveUsers(){
        return activeUsers;
    }

    public void addUser(String name,String mobile,String address){
        User user=new User();
        user.name=name;
        user.mobile=mobile;
        user.address=address;
        user.isActive=true;
        user.createdAt=System.currentTimeMillis();

        repository.insert(user);
    }

    public void updateUser(User user){
        repository.update(user);
    }

    public void deactivateUser(int userId){
        repository.deactivate(userId);
    }


}
