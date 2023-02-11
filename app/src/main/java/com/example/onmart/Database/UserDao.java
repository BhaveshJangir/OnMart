package com.example.onmart.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserInfo")
    List<UserInfo> getUserData();


    @Insert
    Void addUserData(UserInfo userInfo);

    @Update
    Void updateUserOrder(UserInfo oderEntity);

    @Delete
    Void deleteUserOrder(UserInfo oderEntity);
}
