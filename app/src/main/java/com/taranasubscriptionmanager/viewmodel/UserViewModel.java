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
    private final LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // ADD USER
    public void addUser(String name,
                        String mobile,
                        String address,
                        String startDate,
                        int tofuQty,
                        int milkQty) {

        User user = new User(
                name,
                mobile,
                address,
                startDate,
                tofuQty,
                milkQty
        );

        repository.insert(user);
    }

    // UPDATE USER
    public void updateUser(User user) {
        repository.update(user);
    }

    // DELETE USER
    public void deleteUser(User user) {
        repository.delete(user);
    }

    // DEACTIVATE USER
    public void deactivateUser(User user) {
        user.isActive = false;
        repository.update(user);
    }

    // GET ACTIVE USERS
    public LiveData<List<User>> getActiveUsers() {
        return repository.getActiveUsers();
    }

    public LiveData<Integer> getTotalTofuRequired() {
        return repository.getTotalTofuRequired();
    }

    public LiveData<Integer> getTotalMilkRequired() {
        return repository.getTotalMilkRequired();
    }

    public LiveData<Integer> getActiveUsersCount() {
        return repository.getActiveUsersCount();
    }

}