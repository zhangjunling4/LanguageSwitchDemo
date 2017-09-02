package com.seek.languageswitchdemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView content;
    private TextView acto;
    private TextView china;
    private TextView us;
    private  TextView taiWan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        acto.setOnClickListener(this);
        china.setOnClickListener(this);
        taiWan.setOnClickListener(this);
        us.setOnClickListener(this);
    }

    private void findViews() {
        content = (TextView) findViewById(R.id.content);
        acto = (TextView) findViewById(R.id.auto);
        china = (TextView) findViewById(R.id.china);
        taiWan = (TextView) findViewById(R.id.taiwan);
        us = (TextView) findViewById(R.id.us);

    }

    @Override
    public void onClick(View v) {
        Configuration config = new Configuration();
        switch (v.getId()){
            case R.id.auto:
                config.locale = Locale.getDefault();
//                changeAppLanguage();
////                recreate();
//                reStartApp();
                break;
            case R.id.china:
                config.locale = Locale.CHINA;
//                changeAppLanguage();
//                reStartApp();
                break;
            case R.id.taiwan:
                config.locale = Locale.TAIWAN;
//                changeAppLanguage();
//                reStartApp();
                break;
            case R.id.us:
                config.locale = Locale.US;
//                changeAppLanguage();
//                reStartApp();
                break;
        }
        onConfigurationChanged(config);
//        resources.updateConfiguration(config, dm);

        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
//        reStartApp();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getBaseContext().getResources().updateConfiguration(newConfig, getBaseContext().getResources().getDisplayMetrics());
    }

    private void reStartApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        // 杀掉进程
        /*
            假如你的 App 存在某个 activity 和当前设置页 activity 不在一个 task 栈内的话（
            比如你从某个通知页用 FLAG_ACTIVITY_NEW_TASK 启动的一个 activity），
            就不会应用语言设置。因此可以直接杀掉当前 App 的进程，保证是“整个”重启了：
         */
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void changeAppLanguage() {
        //这是SharedPreferences工具类，用于保存设置，代码很简单，自己实现吧
        String sta = LanguageUtils.isChineseLanguage(this) ? "zh" : "en";
        // 本地语言设置
        Locale myLocale = new Locale(sta);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Log.i("Language", "area=" + LanguageUtils.getSystemLanguage(this));
    }
}
