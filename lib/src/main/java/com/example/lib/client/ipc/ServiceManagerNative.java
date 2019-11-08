package com.example.lib.client.ipc;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.lib.client.core.VirtualCore;
import com.example.lib.server.ServiceCache;
import com.example.lib.server.interfaces.IServiceFetcher;

import androidx.annotation.RequiresApi;

public class ServiceManagerNative {


    private static IServiceFetcher sFetcher;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static IServiceFetcher getServiceFetcher() throws RemoteException {
        if (sFetcher == null || !sFetcher.asBinder().isBinderAlive()) {
            synchronized (ServiceManagerNative.class) {
                Context context = VirtualCore.get().getContext();
                Bundle response = context.getContentResolver().acquireContentProviderClient("com.jay.example.providers.myprovider").call("@",null,null);
                if (response != null) {
                    IBinder binder = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        binder = response.getBinder("_VA_|_binder_");
                    }
                    linkBinderDied(binder);
                    sFetcher = IServiceFetcher.Stub.asInterface(binder);
                }
            }
        }
        return sFetcher;
    }
    private static void linkBinderDied(final IBinder binder) {
        IBinder.DeathRecipient deathRecipient =new IBinder.DeathRecipient() {
            @Override
            public void binderDied() {
                binder.unlinkToDeath(this,0);
            }
        };
        try {
            binder.linkToDeath(deathRecipient, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        public static IBinder getService(String serverName) throws RemoteException {
        if (VirtualCore.get().isServerProcess()) {
            return ServiceCache.getService(serverName);
        }
        IServiceFetcher fetcher = getServiceFetcher();
        if(fetcher == null){
            try{
                return fetcher.getService(serverName);
            }catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
