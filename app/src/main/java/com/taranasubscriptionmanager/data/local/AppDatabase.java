package com.taranasubscriptionmanager.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.taranasubscriptionmanager.data.model.Product;
import com.taranasubscriptionmanager.data.model.Subscription;
import com.taranasubscriptionmanager.data.model.User;
import com.taranasubscriptionmanager.data.model.Delivery;

@Database(
        entities = {
                Product.class,
                User.class,
                Subscription.class,
                Delivery.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "tarana_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ProductDao productDao();
    public abstract UserDao userDao();
    public abstract DeliveryDao deliveryDao();
}