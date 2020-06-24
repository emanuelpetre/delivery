package com.example.delivery.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface OrderDAO {

    @Query("Select * from Orders")
    LiveData<List<Order>> getAllOrders();


    @Delete
    void delete (Order order);


    @Insert
    void insert (Order order);

    @Update
    void update(Order order);

    @Query("DELETE FROM Orders WHERE name LIKE :name")
    int delete(String name);

    @Query("SELECT * FROM orders")
    List<Order> getAll();
}
