package com.example.amdroidtestjava;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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
    }

    @Override
    public void onClick(View view) {
        String name = eName.getText().toString();
        String age = eAge.getText().toString();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name",name);
        editor.putString("age",age);
        editor.putBoolean("married",cMaried.isChecked());
        editor.commit();

    }
}