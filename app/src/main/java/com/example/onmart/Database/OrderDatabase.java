package com.example.onmart.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {OderEntity.class,UserInfo.class},exportSchema = false,version = 2)
public abstract class OrderDatabase extends RoomDatabase {
    private static final String DB_NAME = "Datadb";
    private static OrderDatabase intance;

    public static synchronized OrderDatabase getDb(Context context){
        if(intance == null){
            intance = Room.databaseBuilder(context,OrderDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return intance;
    }

    public abstract OderDao oderDao();
    public abstract UserDao userDao();
}
