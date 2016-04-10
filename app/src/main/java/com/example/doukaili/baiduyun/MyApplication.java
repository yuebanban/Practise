package com.example.doukaili.baiduyun;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Dou.Kaili on 3/31/2016.
 */
public class MyApplication extends Application {
    public void onCreate() {
        super.onCreate();
        //监测网络状况
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(
                        Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(
                        Stetho.defaultInspectorModulesProvider(this))
                    .build()
        );

    }

}
