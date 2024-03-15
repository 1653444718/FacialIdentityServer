package com.utils;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class VerCodeUtil {
    private volatile static ExpiringMap<String, String> verCode = ExpiringMap.builder().variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    public static void setVerCode(String CodeId, String code) {
        verCode.put(CodeId, code, ExpirationPolicy.CREATED, 2, TimeUnit.MINUTES);
    }

    public static String getVerCode(String CodeId) {
        return verCode.get(CodeId);
    }

    public static void removeVerCode(String CodeId) {
        verCode.remove(CodeId);
    }
}
