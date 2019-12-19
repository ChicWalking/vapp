package com.example.lib.IPC;

import android.os.IBinder;
import android.os.RemoteException;

import java.lang.reflect.Proxy;

public class IPCBus {
    private static IServerCache sCache;

    public static void initializa(IServerCache cache){
        sCache = cache;
    }
    private static void checkInitialized() {
        if (sCache == null) {
            throw new IllegalStateException("please call initialize() at first.");
        }
    }
    public static <T> T get(Class<?>interfaceClass) {
        checkInitialized();
        ServerInterface serverInterface = new ServerInterface(interfaceClass);
        IBinder binder = sCache.query(serverInterface.getInterfaceName());
        if (binder == null) {
            return null;
        }
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new IPCInvocationBridge(serverInterface, binder));
    }
    public static void register(Class<?> interfaceClass, Object server) {
        checkInitialized();
        ServerInterface serverInterface = new ServerInterface(interfaceClass);
        TransformBinder binder = new TransformBinder(serverInterface, server);
        sCache.join(serverInterface.getInterfaceName(), binder);
    }
}
