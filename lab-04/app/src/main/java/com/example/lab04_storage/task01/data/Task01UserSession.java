package com.example.lab04_storage.task01.data;

/**
 * Model đại diện cho phiên đăng nhập của SharedPreferences.
 * Dùng để dễ quản lý dữ liệu trong code.
 */
public class Task01UserSession {

    public String username;
    public String password;
    public boolean remember;

    public Task01UserSession(String username, String password, boolean remember) {
        this.username = username;
        this.password = password;
        this.remember = remember;
    }
}
