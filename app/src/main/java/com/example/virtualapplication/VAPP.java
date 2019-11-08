package com.example.virtualapplication;

import android.app.Application;
import android.content.Context;

public class VAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}
