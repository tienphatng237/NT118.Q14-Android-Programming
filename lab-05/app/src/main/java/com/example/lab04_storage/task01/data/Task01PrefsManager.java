package com.example.lab04_storage.task01.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.lab04_storage.task01.utils.Task01Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Quản lý SharedPreferences cho Task 01.
 * Gồm: lưu user, lấy user, xoá dữ liệu.
 */
public class Task01PrefsManager {

    private final SharedPreferences prefs;

    public Task01PrefsManager(Context context) {
        prefs = context.getSharedPreferences(
                Task01Constants.PREF_NAME,
                Context.MODE_PRIVATE
        );

        // Tạo user admin mặc định nếu chưa có
        ensureDefaultAdmin();
    }

    // ==============================
    // QUẢN LÝ DANH SÁCH USER (JSON)
    // ==============================

    /** Tạo admin mặc định nếu chưa tồn tại */
    private void ensureDefaultAdmin() {
        if (!userExists("admin")) {
            addUser("admin", "admin", "admin@gmail.com");
        }
    }

    /** Kiểm tra username đã tồn tại chưa */
    public boolean userExists(String username) {
        ArrayList<Task01UserSession> users = getAllUsers();
        for (Task01UserSession u : users) {
            if (u.username.equals(username)) return true;
        }
        return false;
    }

    /** Thêm user mới */
    public void addUser(String username, String password, String email) {
        try {
            JSONArray arr = new JSONArray(prefs.getString("users", "[]"));

            // Không thêm trùng
            for (int i = 0; i < arr.length(); i++) {
                if (arr.getJSONObject(i).getString("username").equals(username)) {
                    return; // đã tồn tại -> bỏ qua
                }
            }

            JSONObject obj = new JSONObject();
            obj.put("username", username);
            obj.put("password", password);
            obj.put("email", email);

            arr.put(obj);

            prefs.edit().putString("users", arr.toString()).apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /** Xác thực đăng nhập */
    public boolean validateLogin(String username, String password) {
        ArrayList<Task01UserSession> users = getAllUsers();
        for (Task01UserSession u : users) {
            if (u.username.equals(username) && u.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    /** Lấy danh sách toàn bộ user */
    public ArrayList<Task01UserSession> getAllUsers() {
        ArrayList<Task01UserSession> list = new ArrayList<>();

        try {
            JSONArray arr = new JSONArray(prefs.getString("users", "[]"));

            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Task01UserSession(
                        o.getString("username"),
                        o.getString("password"),
                        false
                ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ==============================
    // SESSION CŨ GIỮ NGUYÊN
    // ==============================

    /** Lưu username, password và trạng thái remember */
    public void saveSession(String username, String password, boolean remember) {
        prefs.edit()
                .putString(Task01Constants.KEY_USERNAME, username)
                .putString(Task01Constants.KEY_PASSWORD, password)
                .putBoolean(Task01Constants.KEY_REMEMBER, remember)
                .apply();
    }

    /** Lấy session đã lưu (nếu có) */
    public Task01UserSession getSession() {
        String u = prefs.getString(Task01Constants.KEY_USERNAME, "");
        String p = prefs.getString(Task01Constants.KEY_PASSWORD, "");
        boolean r = prefs.getBoolean(Task01Constants.KEY_REMEMBER, false);

        return new Task01UserSession(u, p, r);
    }

    // =========== Dark Mode ===========
    public boolean isDarkMode() {
        return prefs.getBoolean("dark_mode", false);
    }

    public void setDarkMode(boolean enabled) {
        prefs.edit().putBoolean("dark_mode", enabled).apply();
    }

    // =========== Export JSON ===========
    public String exportToJson() {
        Task01UserSession user = getSession();
        return "{ \"username\": \"" + user.username + "\" }";
    }

    /** Xoá sạch dữ liệu */
    public void clear() {
        prefs.edit().clear().apply();
        ensureDefaultAdmin(); // luôn tạo lại admin
    }
}
