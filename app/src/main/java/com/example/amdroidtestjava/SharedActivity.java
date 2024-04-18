package com.example.amdroidtestjava;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.amdroidtestjava.database.UserDBHelper;
import com.example.amdroidtestjava.enity.User;
import com.example.amdroidtestjava.util.Utils;

import java.util.List;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener {

    EditText eName;
    EditText eAge;
    Button submitInf;
    CheckBox cMaried;
    SharedPreferences sharedPreferences;
    Button creatTable;
    Button deleteTable;
    TextView selectResult;
    String dbPath;
    Button insertDB;
    Button deleteDB;
    Button updateDB;
    Button selectDB;
    UserDBHelper userDBHelper;
    Button acidButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.shared), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intitView();
        creatTable.setOnClickListener(this);
        deleteTable.setOnClickListener(this);
        insertDB.setOnClickListener(this);
        deleteDB.setOnClickListener(this);
        updateDB.setOnClickListener(this);
        selectDB.setOnClickListener(this);
        submitInf.setOnClickListener(this);
        acidButton.setOnClickListener(this);
        //指定配置文件的名称
        sharedPreferences = getSharedPreferences("sharedActivityConfig",MODE_PRIVATE);
        //获取配置文件
        reloadSharedActivityConfig();
        //数据库路径
        dbPath = getFilesDir() + "/test.db";

    }

    @Override
    protected void onStart() {
        super.onStart();
        userDBHelper = UserDBHelper.getInstance(this);
        userDBHelper.openReadLink();
        userDBHelper.openWriteLink();
//        userDBHelper.onCreate(userDBHelper.getWritableDatabase());
    }

    @Override
    protected void onStop() {
        super.onStop();
        userDBHelper.closeLink();
    }

    private void reloadSharedActivityConfig() {
        //取name 值 ,如果取不到 设置默认值为 “”
        String name = sharedPreferences.getString("name", "");
        String age = sharedPreferences.getString("age","");
        Boolean marrid = sharedPreferences.getBoolean("married",false);
        eName.setText(name);
        eAge.setText(age);
        cMaried.setChecked(marrid);
    }

    private void intitView() {
        eName = findViewById(R.id.eName);
        eAge = findViewById(R.id.eAge);
        submitInf = findViewById(R.id.submitInf);
        cMaried = findViewById(R.id.cMaried);
        deleteTable = findViewById(R.id.deleteTable);
        creatTable = findViewById(R.id.creatTable);
        selectResult = findViewById(R.id.selectResult);
        insertDB = findViewById(R.id.insertDB);
        deleteDB = findViewById(R.id.deleteDB);
        updateDB = findViewById(R.id.updateDB);
        selectDB = findViewById(R.id.selectDB);
        acidButton = findViewById(R.id.acidButton);
    }

    @Override
    public void onClick(View view) {
        User user = new User();
        user.setAge(eAge.getText().toString());
        user.setName(eName.getText().toString());
        user.setMarried(cMaried.isChecked());
        if(R.id.submitInf == view.getId()){
            String name = eName.getText().toString();
            String age = eAge.getText().toString();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name",name);
            editor.putString("age",age);
            editor.putBoolean("married",cMaried.isChecked());
            editor.commit();
        }else if(R.id.creatTable == view.getId()){
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(dbPath, Context.MODE_PRIVATE, null);
            String desc = String.format("数据库%s创建%s",dbPath,(sqLiteDatabase==null)?"失败":"成功");
            selectResult.setText(desc);

        }else if(R.id.deleteTable == view.getId()){
            boolean bool = deleteDatabase(dbPath);
            String desc = String.format("数据库%s删除%s",dbPath,bool?"成功":"失败");
            selectResult.setText(desc);

        }else if(R.id.insertDB == view.getId()){

            Long result = userDBHelper.insert(user);
            String desc = String.format("插入数据: %s \n 结果%s",user,result>0?"成功":"失败");
            selectResult.setText(desc);
            Utils.toastShow(this,result>0?"成功":"失败");

        }else if(R.id.deleteDB == view.getId()){
            int result = userDBHelper.deleteByNameAndAge(user);
            String desc = String.format("删除数据: %s \n 结果%s",user,result>0?"成功":"失败");
            selectResult.setText(desc);
            Utils.toastShow(this,result>0?"成功":"失败");

        }else if(R.id.updateDB == view.getId()){
            int result = userDBHelper.updateByName(user);
            String desc = String.format("修改数据: %s \n 结果%s",user,result>0?"成功":"失败");
            selectResult.setText(desc);
            Utils.toastShow(this,result>0?"成功":"失败");

        }else if(R.id.selectDB == view.getId()){
            List<User> userList = userDBHelper.selectByName(user);
            String  desc = String.format("查询数据结果为: \n" + userList.toString());
            selectResult.setText(desc);

        }else if(R.id.acidButton == view.getId()){
            userDBHelper.acidTest();
        }



    }
}