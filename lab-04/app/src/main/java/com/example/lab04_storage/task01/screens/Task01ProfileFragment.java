package com.example.lab04_storage.task01.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task01.data.Task01PrefsManager;
import com.example.lab04_storage.task01.data.Task01UserSession;
import com.example.lab04_storage.task02.ui.main.Task02MainActivity;

public class Task01ProfileFragment extends Fragment {

    private Task01PrefsManager prefs;

    public Task01ProfileFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.feature_task01_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prefs = new Task01PrefsManager(requireContext());

        TextView tvUsername = view.findViewById(R.id.tvUsername);
        Switch switchTheme = view.findViewById(R.id.switchTheme);
        Button btnExport = view.findViewById(R.id.btnExport);
        Button btnLogout = view.findViewById(R.id.btnLogout);
        Button btnSettings = view.findViewById(R.id.btnSettings);

        Task01UserSession session = prefs.getSession();

        tvUsername.setText("Xin chào, " + session.username + "!");

        // DARK MODE
        switchTheme.setChecked(prefs.isDarkMode());
        switchTheme.setOnCheckedChangeListener((btn, isChecked) -> {
            prefs.setDarkMode(isChecked);
            AppCompatDelegate.setDefaultNightMode(
                    isChecked ? AppCompatDelegate.MODE_NIGHT_YES :
                            AppCompatDelegate.MODE_NIGHT_NO);
        });

        // EXPORT JSON
        btnExport.setOnClickListener(v -> {
            String json = prefs.exportToJson();
            Toast.makeText(requireContext(), json, Toast.LENGTH_LONG).show();
        });

        // LOGOUT
        btnLogout.setOnClickListener(v -> {
            prefs.clear();
            startActivity(new Intent(requireContext(), Task01LoginActivity.class));
            requireActivity().finishAffinity();
        });

        // SETTINGS (TASK 2)
        btnSettings.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), Task02MainActivity.class))
        );
    }
}
