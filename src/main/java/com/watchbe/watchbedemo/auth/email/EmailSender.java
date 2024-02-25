package com.watchbe.watchbedemo.auth.email;

public interface EmailSender {
    void send(String to, String email);
}
