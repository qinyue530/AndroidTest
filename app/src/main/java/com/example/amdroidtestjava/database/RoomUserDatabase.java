package com.example.amdroidtestjava.database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.amdroidtestjava.dao.RoomUserDao;
import com.example.amdroidtestjava.enity.RoomUser;
@Database(entities = {RoomUser.class},version = 1,exportSchema = true)
public abstract class RoomUserDatabase extends RoomDatabase {

    public abstract RoomUserDao roomUserDao();



}
