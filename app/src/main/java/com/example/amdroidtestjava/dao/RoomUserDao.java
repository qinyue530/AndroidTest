package com.example.amdroidtestjava.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.amdroidtestjava.enity.RoomUser;

import java.util.List;

@Dao
public interface RoomUserDao {

    @Insert
    void insert(RoomUser... roomUsers);

    @Delete
    void delete(RoomUser... roomUsers);
    @Query("DELETE FROM ROOMUSER")
    void deleteAll();

    @Update()
    void update(RoomUser... roomUsers);

    @Query("SELECT * FROM ROOMUSER")
    List<RoomUser> queryAll();
    @Query("SELECT * FROM ROOMUSER WHERE NAME = :name order by ID")
    List<RoomUser> queryByName(String name);

}
