package com.utils;


import com.entity.pojo.User;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class AuthUtil {
    private volatile static ExpiringMap<String, User> tokenMap = ExpiringMap.builder().variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    public static void setToken(String token, User user) {
        tokenMap.put(token, user, ExpirationPolicy.ACCESSED, 30, TimeUnit.MINUTES);
    }

    public static User getUser(String token) {
        return tokenMap.get(token);
    }

    public static void removeToken(String token) {
        tokenMap.remove(token);
    }

}
