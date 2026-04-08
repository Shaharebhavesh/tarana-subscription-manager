package com.taranasubscriptionmanager.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.Pager;

import com.taranasubscriptionmanager.data.local.AppDatabase;
import com.taranasubscriptionmanager.data.local.DeliveryDao;
import com.taranasubscriptionmanager.data.local.UserDao;
import com.taranasubscriptionmanager.data.model.Delivery;
import com.taranasubscriptionmanager.data.model.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private final DeliveryDao deliveryDao;

    private final LiveData<List<User>> allUsers;

    private final ExecutorService executorService =
            Executors.newSingleThreadExecutor();

    public UserRepository(Application application) {

        AppDatabase db = AppDatabase.getDatabase(application);

        userDao = db.userDao();
        deliveryDao = db.deliveryDao();

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

    // TOTAL TOFU REQUIRED
    public LiveData<Integer> getTotalTofuRequired() {
        return userDao.getTotalTofuRequired();
    }

    // TOTAL MILK REQUIRED
    public LiveData<Integer> getTotalMilkRequired() {
        return userDao.getTotalMilkRequired();
    }

    // ACTIVE USERS COUNT
    public LiveData<Integer> getActiveUsersCount() {
        return userDao.getActiveUsersCount();
    }

    // ✅ PAGINATION (FIXED)
    public LiveData<PagingData<User>> getPagedUsers() {

        Pager<Integer, User> pager = new Pager<>(
                new PagingConfig(
                        10,     // page size
                        10,
                        false
                ),
                () -> userDao.getPagedUsers()
        );

        return PagingLiveData.getLiveData(pager);
    }

    // GENERATE TODAY DELIVERIES
    public void generateTodayDeliveries(List<User> users){

        long today = System.currentTimeMillis();

        executorService.execute(() -> {

            if(deliveryDao.getTodayDeliveryCount(today) > 0){
                return;
            }

            for(User user : users){

                Delivery delivery = new Delivery();

                delivery.userId = user.id;
                delivery.date = today;
                delivery.tofuQty = user.tofuQty;
                delivery.milkQty = user.milkQty;

                delivery.totalAmount =
                        (user.tofuQty * 50) +
                                (user.milkQty * 15);

                deliveryDao.insert(delivery);
            }

        });
    }
}