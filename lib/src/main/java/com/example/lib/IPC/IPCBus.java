package com.example.lib.IPC;

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
    public static <T> T get(Class<?>interfaceClass){
        checkInitialized();
        return null;
    }
    public static void register(Class<?> interfaceClass, Object server) {
        checkInitialized();
    }
}
