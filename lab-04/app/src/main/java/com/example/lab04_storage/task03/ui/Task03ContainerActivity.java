package com.example.lab04_storage.task03.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task03.ui.selection.Task03StorageSelectionFragment;

public class Task03ContainerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task03_container);

        if (savedInstanceState == null) {
            loadFragment(new Task03StorageSelectionFragment());
        }
    }

    public void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .commit();
    }
}
