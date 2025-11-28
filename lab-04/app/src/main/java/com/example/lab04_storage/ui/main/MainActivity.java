package com.example.lab04_storage.ui.main;

import android.content.Intent;  // ⬅ FIX LỖI "cannot find symbol Intent"
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.example.lab04_storage.task01.screens.Task01ProfileFragment;   // ⬅ thay Activity bằng Fragment
import com.example.lab04_storage.task02.ui.main.Task02MainActivity;
import com.example.lab04_storage.task03.ui.Task03ContainerActivity;      // ⬅ thay Activity bằng Fragment
import com.example.lab04_storage.task03.ui.Task03ContainerFragment;      // ⬅ thay Activity bằng Fragment

import com.example.lab04_storage.task04.screens.Task04ClassManagerFragment;


public class MainActivity extends AppCompatActivity {

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task_manager_main);

        // ==========================
        // Load Session từ Task01 (KHÔNG dùng nữa)
        // ==========================
        prefs = new Task01PrefsManager(this);
        Task01UserSession session = prefs.getSession();

        // ==========================
        // Navigation bottom bar
        // ==========================
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navTasks = findViewById(R.id.nav_tasks);
        LinearLayout navNoteTask3 = findViewById(R.id.nav_note_task3);
        LinearLayout navDbTask4 = findViewById(R.id.nav_db_task4);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        // ==========================
        // HOME – Load HomeFragment
        // ==========================
        navHome.setOnClickListener(v -> {
            clearBackStack();
            loadHomeFragment();
        });

        // ==========================
        // TASKS – mở Task02 Activity
        // ==========================
        navTasks.setOnClickListener(v -> {
            startActivity(new Intent(this, Task02MainActivity.class));
        });

        // ==========================
        // NOTE (Task03)
        // ==========================
        navNoteTask3.setOnClickListener(v -> {
            clearBackStack();
            loadFragment(new Task03ContainerFragment());
        });


        // ==========================
        // TABLE (Task04)
        // ==========================
        navDbTask4.setOnClickListener(v -> {
            clearBackStack();
            loadTask04Fragment(new Task04ClassManagerFragment());
        });

        // ==========================
        // PROFILE (Task01)
        // ==========================
        navProfile.setOnClickListener(v -> {
            clearBackStack();
            loadFragment(new Task01ProfileFragment());     // ⬅ đổi từ Activity sang Fragment
        });

        // ==========================
        // Khi mở app → mặc định load Home
        // ==========================
        loadHomeFragment();
    }

    /**
     * Xóa toàn bộ fragment trong backstack
     * tránh bị đè lên nhau gây trắng màn hình
     */
    private void clearBackStack() {
        getSupportFragmentManager().popBackStackImmediate(null,
                getSupportFragmentManager().POP_BACK_STACK_INCLUSIVE);
    }

    /**
     * Load Fragment Task04 vào vùng container chung
     */
    private void loadTask04Fragment(Fragment fragment) {

        // Ẩn Task list
        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        // Ẩn Home
        View home = findViewById(R.id.home_fragment_container);
        if (home != null) home.setVisibility(View.GONE);

        // Hiện vùng fragment
        View container = findViewById(R.id.task03_fragment_container);
        if (container != null) container.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Load Fragment chung (Task03, Task01 Profile)
     */
    private void loadFragment(Fragment fragment) {

        // Ẩn Home
        View home = findViewById(R.id.home_fragment_container);
        if (home != null) home.setVisibility(View.GONE);

        // Ẩn Task list
        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        // Hiện container fragment
        View container = findViewById(R.id.task03_fragment_container);
        if (container != null) container.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .commit();
    }

    /**
     * Load Home Fragment
     */
    private void loadHomeFragment() {

        // Ẩn Task list
        View rv = findViewById(R.id.rv_tasks);
        if (rv != null) rv.setVisibility(View.GONE);

        // Ẩn Task03/Task04 container
        View t3 = findViewById(R.id.task03_fragment_container);
        if (t3 != null) t3.setVisibility(View.GONE);

        // Hiện Home container
        View home = findViewById(R.id.home_fragment_container);
        if (home != null) home.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.home_fragment_container, new HomeFragment())
                .commit();
    }
}
