package com.example.virtualapplication;

import android.app.Application;
import android.content.Context;

import com.example.lib.client.core.VirtualCore;

public class VAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        VirtualCore.get().startup(base);

    }
}
