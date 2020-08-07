package com.example.gouwuproject.app;

import android.app.Application;

import com.example.httplibrary.HttpConstant;
import com.example.httplibrary.HttpGlobalConfig;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.commonsdk.UMConfigure;

public class ShopApplication extends Application {
    String WANANDROID = "https://wanandroid.com/";

    @Override
    public void onCreate() {
        super.onCreate();

        youmeng();

        HttpGlobalConfig.getInsance()
                .setBaseUrl(WANANDROID)
//                .setBaseUrl("http://169.254.39.119:8080/kotlin/")
                .setTimeout(HttpConstant.TIME_OUT)
                .setShowLog(true)
                .setTimeUnit(HttpConstant.TIME_UNIT)
                .initReady(this);

        //检测内存泄漏
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

//测试腾讯Bugly
//        CrashReport.initCrashReport(getApplicationContext(), "658c361ea9", false);
//        CrashReport.testJavaCrash();
    }

    private void youmeng() {
        //友盟集成统计
        UMConfigure.init(this, "5f2bf39ab4b08b653e91edf5", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        UMConfigure.setLogEnabled(true);
    }
}
