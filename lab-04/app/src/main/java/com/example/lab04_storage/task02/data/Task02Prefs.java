package com.example.lab04_storage.task02.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Task02Prefs {

    private final SharedPreferences prefs;

    public Task02Prefs(Context context) {
        prefs = context.getSharedPreferences("task02_prefs", Context.MODE_PRIVATE);
    }

    // Setting 1
    public void setRedBackground(boolean value) {
        prefs.edit().putBoolean("red_bg", value).apply();
    }

    public boolean isRedBackground() {
        return prefs.getBoolean("red_bg", false);
    }

    // Setting 2
    public void setSpecialMode(boolean value) {
        prefs.edit().putBoolean("special_mode", value).apply();
    }

    public boolean isSpecialMode() {
        return prefs.getBoolean("special_mode", false);
    }
}
