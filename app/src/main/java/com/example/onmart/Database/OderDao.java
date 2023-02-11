package com.example.onmart.Database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OderDao {

    @Query("SELECT * FROM OderEntity")
    List<OderEntity> getOrderData();


    @Query("Delete from OderEntity")
    Void truncateOrders();

    @Insert
    Void addUserOrder(OderEntity oderEntity);

    @Update
    Void updateUserOrder(OderEntity oderEntity);

    @Delete
    Void deleteUserOrder(OderEntity oderEntity);

}
