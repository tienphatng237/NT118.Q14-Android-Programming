package com.example.lab04_storage.task03.ui.selection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab04_storage.R;
import com.example.lab04_storage.task03.ui.external.Task03ExternalStorageFragment;
import com.example.lab04_storage.task03.ui.internal.Task03InternalStorageFragment;

public class Task03StorageSelectionFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.feature_task03_storage_selection, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button btnInternal = view.findViewById(R.id.btn_select_internal);
        Button btnExternal = view.findViewById(R.id.btn_select_external);

        // Điều hướng đến các Fragment tương ứng
        btnInternal.setOnClickListener(v -> openFragment(new Task03InternalStorageFragment()));
        btnExternal.setOnClickListener(v -> openFragment(new Task03ExternalStorageFragment()));
    }


    private void openFragment(Fragment fragment) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.task03_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}
