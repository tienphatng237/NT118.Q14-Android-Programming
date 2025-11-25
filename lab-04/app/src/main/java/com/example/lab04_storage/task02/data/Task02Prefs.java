package com.example.lab04_storage.task02.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Task02Prefs {
    private static final String PREF = "task02_prefs";
    private static final String KEY_COLOR_RED = "color_red";
    private static final String KEY_INFO_CHECKED = "info_checked";

    private final SharedPreferences sp;

    public Task02Prefs(Context ctx) {
        sp = ctx.getSharedPreferences(PREF, Context.MODE_PRIVATE);
    }

    public boolean isColorRed() {
        return sp.getBoolean(KEY_COLOR_RED, false);
    }

    public void setColorRed(boolean value) {
        sp.edit().putBoolean(KEY_COLOR_RED, value).apply();
    }

    public boolean isInfoChecked() {
        return sp.getBoolean(KEY_INFO_CHECKED, false);
    }

    public void setInfoChecked(boolean value) {
        sp.edit().putBoolean(KEY_INFO_CHECKED, value).apply();
    }
}
