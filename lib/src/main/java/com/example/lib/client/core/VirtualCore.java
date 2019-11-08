package com.example.lib.client.core;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.lib.IPC.IPCBus;
import com.example.lib.IPC.IServerCache;
import com.example.lib.client.ipc.ServiceManagerNative;
import com.example.lib.server.ServiceCache;

import androidx.annotation.RequiresApi;

public class VirtualCore {

    private static VirtualCore gCore = new VirtualCore();
    private ProcessType processType;
    private Context context;


    private VirtualCore(){

    }
    public boolean isServerProcess() {
        return ProcessType.Server == processType;
    }

    public static VirtualCore get(){
        return gCore;
    }

    public  void startup(Context context){
        this.context = context;
        IPCBus.initializa(new IServerCache() {
            @Override
            public void join(String serverName, IBinder binder) {
                ServiceCache.addService(serverName, binder);
            }

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public IBinder query(String serverName) throws RemoteException {
                return ServiceManagerNative.getService(serverName);
            }
        });

    }

    private enum ProcessType {
        /**
         * Server process
         */
        Server,
        /**
         * Virtual app process
         */
        VAppClient,
        /**
         * Main process
         */
        Main,
        /**
         * Child process
         */
        CHILD
    }

    public Context getContext() {
        return context;
    }

}
