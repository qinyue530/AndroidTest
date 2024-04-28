package com.example.amdroidtestjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.amdroidtestjava.dao.RoomUserDao;
import com.example.amdroidtestjava.database.RoomUserDatabase;
import com.example.amdroidtestjava.enity.RoomUser;

import java.util.List;

public class JetpackRoomActivity extends AppCompatActivity implements View.OnClickListener {

    EditText roomName;
    EditText roomAge;
    CheckBox roomMaried;
    Button insertRoomUser;
    Button deleteRoomUser;
    Button updateRoomUser;
    Button selectRoomUser;
    Button queryAllRoomUser;
    Button delAllRoomUser;
    TextView roomResult;
    RoomUserDatabase roomUserDatabase;
    RoomUserDao roomUserDao;
    int roomUserId;

    RoomUser roomUser = new RoomUser();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jetpack_room);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.jetpackRoom), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        roomUserDatabase =  Room.databaseBuilder(this,RoomUserDatabase.class,"RoomUser")
                .addMigrations() //允许迁移数据库,room默认是删除旧库 创建新库 会导致数据丢失
                .allowMainThreadQueries() //允许在主线程中操作数据库,默认是不允许的,通常也不打开,初学打开学习的
                .build();
        roomUserDao = roomUserDatabase.roomUserDao();
        initView();
        initViewOnClickListener();

    }

    private void initView() {
        roomAge = findViewById(R.id.roomAge);
        roomName = findViewById(R.id.roomName);
        roomMaried = findViewById(R.id.roomMaried);
        insertRoomUser = findViewById(R.id.insertRoomUser);
        deleteRoomUser = findViewById(R.id.deleteRoomUser);
        updateRoomUser = findViewById(R.id.updateRoomUser);
        selectRoomUser = findViewById(R.id.selectRoomUser);
        queryAllRoomUser = findViewById(R.id.queryAllRoomUser);
        delAllRoomUser = findViewById(R.id.delAllRoomUser);
        roomResult = findViewById(R.id.roomResult);
    }

    private void initViewOnClickListener() {
        insertRoomUser.setOnClickListener(this);
        deleteRoomUser.setOnClickListener(this);
        updateRoomUser.setOnClickListener(this);
        selectRoomUser.setOnClickListener(this);
        queryAllRoomUser.setOnClickListener(this);
        delAllRoomUser.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = roomName.getText().toString();
        String age = roomAge.getText().toString();
        Boolean married = roomMaried.isActivated();
        roomUser.setMarried(married);
        roomUser.setAge(age);
        roomUser.setName(name);
        roomUser.setId(roomUserId);
        if(view.getId() == insertRoomUser.getId()){
            roomUserDao.insert(roomUser);
        }else if(view.getId() == deleteRoomUser.getId()){
            roomUserDao.delete(roomUser);
        }else if(view.getId() == updateRoomUser.getId()){
            roomUserDao.update(roomUser);
        }else if(view.getId() == selectRoomUser.getId()){
            roomUser = roomUserDao.queryByName(name).get(0);
            roomUserId = roomUser.getId();
        }else if(view.getId() == queryAllRoomUser.getId()){
            roomUser = roomUserDao.queryAll().get(0);
            roomUserId = roomUser.getId();
        }else if(view.getId() == delAllRoomUser.getId()){
            roomUserDao.deleteAll();
        }

    }
}