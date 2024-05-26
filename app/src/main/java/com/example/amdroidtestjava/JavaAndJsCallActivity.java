package com.example.amdroidtestjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.amdroidtestjava.util.Utils;

public class JavaAndJsCallActivity extends AppCompatActivity implements View.OnClickListener {

    WebView webView;
    Button callJs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_java_and_js_call);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.javaAndJsCall), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initWebView();
        callJs = findViewById(R.id.callJs);
        callJs.setOnClickListener(this);
//        setContentView(webView);
    }

    private void javaCallJs( String  arg){
        String str = "{'arg':'"+arg+"'}";
        Log.i("======","==================" + str);
        webView.loadUrl("javascript:javaCallJs("+ str+")");
//        setContentView(webView);
    }

    private void initWebView() {
        //加载网页,H5,Html , 自定义浏览器
        webView = findViewById(R.id.localWebView);
        WebSettings webSettings = webView.getSettings();
        //设置执行js
        webSettings.setJavaScriptEnabled(true);
        //加载网络网页 和 本地网页
        //webView.loadUrl("https://www.baidu.com/");
        //加载本地的H5页面
        webView.loadUrl("file:///android_asset/H5/AndroidAndHtml.html");
        //不调用浏览器
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        //在js中使用window.第二个参数.@JavascriptInterface注解方法名
        //window.JsCallAndroid.callFromJs() 即可调用 callFromJs方法
        webView.addJavascriptInterface(this,"JsCallAndroid");

    }

    @Override
    public void onClick(View v) {
        javaCallJs("安卓调用JS方法");
    }

    @JavascriptInterface
    public String callFromJs(String str){
        Utils.toastShow(this,"Js调用java函数");
        str = "java函数返回值";
        return str;
    }
}