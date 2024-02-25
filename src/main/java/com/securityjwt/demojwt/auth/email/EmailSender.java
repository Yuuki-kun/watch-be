package com.securityjwt.demojwt.auth.email;

public interface EmailSender {
    void send(String to, String email);
}
