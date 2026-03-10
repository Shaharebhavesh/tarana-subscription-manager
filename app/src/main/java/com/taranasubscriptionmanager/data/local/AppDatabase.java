package com.taranasubscriptionmanager.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.taranasubscriptionmanager.data.model.Delivery;
import com.taranasubscriptionmanager.data.model.User;

@Database(entities = {User.class, Delivery.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract DeliveryDao deliveryDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {

        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {

                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "tarana_database"
                            )
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }

        return INSTANCE;
    }
}