package com.example.virtualapplication;

import android.content.Context;

public class VirtualCore {

    private static VirtualCore gCore = new VirtualCore();

    private VirtualCore(){

    }

    public static VirtualCore get(){
        return gCore;
    }

    public  void startup(Context context){
        IPCBus

    }
}
