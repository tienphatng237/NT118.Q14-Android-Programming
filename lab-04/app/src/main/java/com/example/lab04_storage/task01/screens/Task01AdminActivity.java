package com.example.lab04_storage.task01.screens;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;

import java.util.ArrayList;

public class Task01AdminActivity extends AppCompatActivity {

    private ListView listUsers;
    private Button btnLogout;

    private Task01PrefsManager prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feature_task01_admin);

        prefs = new Task01PrefsManager(this);

        listUsers = findViewById(R.id.listUsers);
        btnLogout = findViewById(R.id.btnLogoutAdmin);

        ArrayList<Task01UserSession> list = prefs.getAllUsers();
        ArrayList<String> names = new ArrayList<>();

        for (Task01UserSession u : list) {
            names.add(u.username + " (" + u.password + ")");
        }

        listUsers.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        ));

        btnLogout.setOnClickListener(v -> {
            prefs.saveSession("", "", false);
            finish();
        });
    }
}
