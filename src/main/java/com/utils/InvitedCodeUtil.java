package com.utils;

import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;

import java.util.concurrent.TimeUnit;

public class InvitedCodeUtil {
    private volatile static ExpiringMap<String, String> invitedCode = ExpiringMap.builder().variableExpiration()
            .expirationPolicy(ExpirationPolicy.CREATED)
            .build();

    public static void setInvitedCode(String CodeId, String code) {
        invitedCode.put(CodeId, code, ExpirationPolicy.ACCESSED, 15, TimeUnit.MINUTES);
    }

    public static String getInvitedCode(String CodeId) {
        return invitedCode.get(CodeId);
    }

    public static void removeInvitedCode(String CodeId) {
        invitedCode.remove(CodeId);
    }
}
