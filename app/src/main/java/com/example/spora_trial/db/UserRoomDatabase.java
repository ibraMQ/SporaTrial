package com.example.spora_trial.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static volatile UserRoomDatabase INSTANCE;

    public static UserRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (UserRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
