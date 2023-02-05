package com.mjc.stage2;

public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    private static final Object lock = new Object();

    private ThreadSafeSingleton() {}

    public static ThreadSafeSingleton getInstance(){
        if (instance == null){
            synchronized (lock) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
