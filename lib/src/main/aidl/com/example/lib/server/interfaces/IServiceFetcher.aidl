// IServiceFetcher.aidl
package com.example.lib.server.interfaces;

// Declare any non-default types here with import statements

interface IServiceFetcher {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder getService(String name);
    void addService(String name,in IBinder service);
    void removeService(String name);
}
