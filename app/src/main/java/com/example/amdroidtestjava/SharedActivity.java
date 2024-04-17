package com.example.amdroidtestjava;

import android.content.Context;
import android.content.SharedPreferences;
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
        submitInf.setOnClickListener(this);
        //指定配置文件的名称
        sharedPreferences = getSharedPreferences("sharedActivityConfig",MODE_PRIVATE);
        //获取配置文件
        reloadSharedActivityConfig();
        //数据库路径
        dbPath = getFilesDir() + "/test.db";
        creatTable.setOnClickListener(this);
        deleteTable.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View view) {
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
            String desc = String.format("数据库%s创建%s",dbPath,bool?"成功":"失败");
            selectResult.setText(desc);

        }



    }
}