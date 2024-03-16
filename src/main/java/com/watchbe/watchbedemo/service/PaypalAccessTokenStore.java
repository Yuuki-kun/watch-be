package com.watchbe.watchbedemo.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PaypalAccessTokenStore {
    private ConcurrentHashMap<String, AccessTokenInfo> paypalAccessTokenStore = new ConcurrentHashMap<>();
    public void saveAccessToken(String key, String token, int expiresIn){
        LocalDateTime expiryTime = LocalDateTime.now().plus(expiresIn, ChronoUnit.SECONDS);
        paypalAccessTokenStore.put(key, new AccessTokenInfo(token, expiryTime));
    }
    public String getAccessToken(String key){

        AccessTokenInfo accessTokenInfo = paypalAccessTokenStore.get(key);
        if (accessTokenInfo != null && accessTokenInfo.isNotExpired()) {
            return accessTokenInfo.getToken();
        }
        return null;
    }

    public void removeAccessToken(String key){
        paypalAccessTokenStore.remove(key);
    }

    private static class AccessTokenInfo {
        private String token;
        private LocalDateTime expiryTime;

        public AccessTokenInfo(String token, LocalDateTime expiryTime) {
            this.token = token;
            this.expiryTime = expiryTime;
        }

        public String getToken() {
            return token;
        }

        public boolean isNotExpired() {
            System.out.println("expiry time = "+expiryTime);
            return LocalDateTime.now().isBefore(expiryTime);
        }
    }

}
