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

import java.util.ArrayList;
import java.util.List;

public class JetpackRoomActivity extends AppCompatActivity implements View.OnClickListener {

    EditText roomName;
    EditText roomAge;
    EditText roomId;
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
        roomId = findViewById(R.id.roomId);
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
        List<RoomUser> roomUserList = new ArrayList<>();
        String id = roomId.getText().toString();
        String name = roomName.getText().toString();
        String age = roomAge.getText().toString();
        Boolean married = roomMaried.isChecked();
        RoomUser roomUser = new RoomUser();
        roomUser.setMarried(married);
        roomUser.setAge(age);
        roomUser.setName(name);

        if(null != id && !"".equals(id.trim())){
            roomUser.setId(Integer.valueOf(id));
        }

        if(view.getId() == insertRoomUser.getId()){
            RoomUser insertRoomUSer = new RoomUser();
            insertRoomUSer.setName(name);
            insertRoomUSer.setAge(age);
            insertRoomUSer.setMarried(married);
            roomUserDao.insert(insertRoomUSer);
        }else if(view.getId() == deleteRoomUser.getId()){
            roomUserDao.delete(roomUser);
        }else if(view.getId() == updateRoomUser.getId()){
            roomUserDao.update(roomUser);
        }else if(view.getId() == selectRoomUser.getId()){
            roomUserList = roomUserDao.queryByName(name);
        }else if(view.getId() == queryAllRoomUser.getId()){
            roomUserList = roomUserDao.queryAll();
        }else if(view.getId() == delAllRoomUser.getId()){
            roomUserDao.deleteAll();
        }
        if(null != roomUserList && roomUserList.size()>0){
            roomUser = roomUserList.get(0);
            roomId.setText(String.valueOf(roomUser.getId()));
            roomName.setText(roomUser.getName());
            roomAge.setText(roomUser.getAge());
            roomMaried.setActivated(roomUser.getMarried());
            roomResult.setText(roomUserList.toString());
        }else{
            roomResult.setText("");
        }


    }
}