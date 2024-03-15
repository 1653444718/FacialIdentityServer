package com.filter;


import com.entity.pojo.User;

public class ThreadLocalData {
    private static final ThreadLocal<User> threadLocalData = new ThreadLocal<>();

    public static void set(User user) {
        threadLocalData.set(user);
    }

    public static User get() {
        return threadLocalData.get();
    }

    public static void remove() {
        threadLocalData.remove();
    }
}
