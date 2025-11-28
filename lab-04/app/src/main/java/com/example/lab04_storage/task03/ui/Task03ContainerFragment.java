package com.example.lab04_storage.task03.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task03.ui.selection.Task03StorageSelectionFragment;

public class Task03ContainerFragment extends Fragment {

    public Task03ContainerFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_task03_container, container, false);

        // Load màn hình chọn phương thức lưu trữ
        if (savedInstanceState == null) {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.task03_fragment_container, new Task03StorageSelectionFragment())
                    .commit();
        }

        return view;
    }
}
