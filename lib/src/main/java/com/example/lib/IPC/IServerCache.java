package com.example.lib.IPC;

import android.os.IBinder;
import android.os.RemoteException;

public interface IServerCache {
    void join(String serverName, IBinder binder);
    IBinder query(String serverName) throws RemoteException;
}
