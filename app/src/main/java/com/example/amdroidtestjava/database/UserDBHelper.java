package com.example.amdroidtestjava.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.amdroidtestjava.enity.User;
import com.example.amdroidtestjava.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class UserDBHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "USER_INFO";
    private static final String DB_NAME = "user.db";
    private static final int DB_VERSION = 1;
    private static UserDBHelper userDBHelper;
    private static SQLiteDatabase R_DATABASE;
    private static SQLiteDatabase W_DATABASE;
    private UserDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //数据库只读连接
    public SQLiteDatabase openReadLink(){
        if(null == R_DATABASE || !R_DATABASE.isOpen()){
            R_DATABASE = getReadableDatabase();
        }
        return R_DATABASE;
    }

    //数据库写连接
    public SQLiteDatabase openWriteLink(){
        if(null == W_DATABASE || !W_DATABASE.isOpen()){
            W_DATABASE = getWritableDatabase();
        }
        return W_DATABASE;
    }

    public void closeLink(){
        if(null != R_DATABASE || R_DATABASE.isOpen()){
            R_DATABASE.close();
        }
        if(null != W_DATABASE || W_DATABASE.isOpen()){
            W_DATABASE.close();
        }
    }

    public static UserDBHelper getInstance(Context context){
        if(null == userDBHelper){
            userDBHelper = new UserDBHelper(context);
        }
        return userDBHelper;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," +
                "NAME VARCHAR NOT NULL ," +
                "AGE VARCHAR NOT NULL," +
                "MARRIED INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public Long insert(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , user.getName());
        contentValues.put("age",user.getAge());
        contentValues.put("married",user.getMarried());
        return W_DATABASE.insert(TABLE_NAME,null,contentValues);
    }

    public int  deleteByNameAndAge(User user){
        return W_DATABASE.delete(TABLE_NAME,"name =  ? and age = ?",new String[]{user.getName(),user.getAge()});
    }

    public int  updateByName(User user){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" , user.getName());
        contentValues.put("age",user.getAge());
        contentValues.put("married",user.getMarried());
        return W_DATABASE.update(TABLE_NAME,contentValues,"name = ?" , new String[]{user.getName()});
    }

    public List<User> selectByName(User user){
        List<User> userList = new ArrayList<>();
        //返回的值为游标
        Cursor cursor = W_DATABASE.query(TABLE_NAME,null , "name = ?" , new String[]{user.getName()},null,null,null,null);
        while (cursor.moveToNext()){
            User queryUser = new User();
            queryUser.setId(Integer.parseInt(cursor.getString(0)));
            queryUser.setName(cursor.getString(1));
            queryUser.setAge(cursor.getString(2));
            queryUser.setMarried(Boolean.valueOf(cursor.getString(3)));
            userList.add(queryUser);
        }
        return userList;
    }

    //事务控制
    public void acidTest(){
        try {
            W_DATABASE.beginTransaction();
            User userA = new User();
            userA.setName("A");
            userA.setAge("A");
            userA.setMarried(true);
            userDBHelper.insert(userA);
            int i = 1 / 0;
            User userB = new User();
            userB.setName("B");
            userB.setAge("B");
            userB.setMarried(true);
            userDBHelper.insert(userB);
            W_DATABASE.setTransactionSuccessful();
        }catch (Exception e){

        }finally {
            W_DATABASE.endTransaction();
        }

    }


}
