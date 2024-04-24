package com.example.amdroidtestjava;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.database.UserDBHelper;
import com.example.amdroidtestjava.enity.User;
import com.example.amdroidtestjava.util.FileUtils;
import com.example.amdroidtestjava.util.Utils;

import java.io.File;
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
    Button internalStorage;
    Button internalStorageQuery;
    String internalStoragePath = "";
    String saveImagePath = "";
    String imageName = "";
    Button saveImage;
    Button getImage;
    ImageView openImageResult;
    MyApplication myApplication;


    String internalStorageName = System.currentTimeMillis()+".txt";
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
        intitViewOnClickListener();
        //指定配置文件的名称
        sharedPreferences = getSharedPreferences("sharedActivityConfig",MODE_PRIVATE);
        //获取配置文件
        reloadSharedActivityConfig();
        //数据库路径
        dbPath = getFilesDir() + "/test.db";
        //获取全局变量-在内存中获取
        myApplication = MyApplication.getInstance();
        if(null!=myApplication.infoMap&&myApplication.infoMap.get("age")!=null){
            eAge.setText(myApplication.infoMap.get("age"));
        }
        if(null!=myApplication.infoMap&&myApplication.infoMap.get("name")!=null){
            eName.setText(myApplication.infoMap.get("name"));
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        userDBHelper = UserDBHelper.getInstance(this);
        userDBHelper.openReadLink();
        userDBHelper.openWriteLink();
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
        internalStorage = findViewById(R.id.internalStorage);
        internalStorageQuery = findViewById(R.id.internalStorageQuery);
        saveImage = findViewById(R.id.saveImage);
        getImage = findViewById(R.id.getImage);
        openImageResult = findViewById(R.id.openImageResult);
    }

    private void intitViewOnClickListener() {
        creatTable.setOnClickListener(this);
        deleteTable.setOnClickListener(this);
        insertDB.setOnClickListener(this);
        deleteDB.setOnClickListener(this);
        updateDB.setOnClickListener(this);
        selectDB.setOnClickListener(this);
        submitInf.setOnClickListener(this);
        acidButton.setOnClickListener(this);
        internalStorage.setOnClickListener(this);
        internalStorageQuery.setOnClickListener(this);
        saveImage.setOnClickListener(this);
        getImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        User user = new User();
        user.setAge(eAge.getText().toString());
        user.setName(eName.getText().toString());
        //存储全局变量-放在内存中
        myApplication = MyApplication.getInstance();
        myApplication.infoMap.put("name",eName.getText().toString());
        myApplication.infoMap.put("age",eAge.getText().toString());
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
            //事务
            userDBHelper.acidTest();
        }else if(R.id.internalStorage == view.getId()){
            //外部存储的私有空间
            //internalStoragePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
            //外部存储的公共空间 需要在 AndroidManifest中配置权限
            internalStoragePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(user.toString());
            FileUtils.savText(internalStoragePath+ File.separatorChar+internalStorageName , stringBuilder.toString());
            Log.e("qinyue文件存储", internalStoragePath+ File.separatorChar+internalStorageName );
            Utils.toastShow(this,"外部空间存储成功");

        }else if(R.id.internalStorageQuery == view.getId()){
            String desc = FileUtils.openText(internalStoragePath+ File.separatorChar+internalStorageName);
            Log.e("qinyue文件读取", internalStoragePath+ File.separatorChar+internalStorageName );
            selectResult.setText(desc);
            Utils.toastShow(this,"外部空间查询成功");
        }else if(R.id.saveImage == view.getId()){
            imageName = "tigger.png";
            //外部存储的私有空间
            saveImagePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()+imageName;
            //从指定资源中获取位图对象
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.tigger);
            FileUtils.saveImage(saveImagePath,bitmap);
            Utils.toastShow(this,"图片保存成功");
        }else if(R.id.getImage == view.getId()){
            imageName = "tigger.png";
            saveImagePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString()+imageName;
            //三种获取图片的方式
            //第一种
            //Bitmap bitmap = FileUtils.openImage(saveImagePath);
            //openImageResult.setImageBitmap(bitmap);
            //第二种
            //Bitmap bitmap = BitmapFactory.decodeFile(saveImagePath);
            //openImageResult.setImageBitmap(bitmap);
            //第三种
            openImageResult.setImageURI(Uri.parse(saveImagePath));
            Utils.toastShow(this,"图片获取成功");
        }



    }
}