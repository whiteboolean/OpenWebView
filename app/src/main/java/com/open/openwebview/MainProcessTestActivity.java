package com.open.openwebview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.open.openwebview.databinding.ActivtyMainProcessTestBinding;

public class MainProcessTestActivity extends AppCompatActivity {

    private ActivtyMainProcessTestBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivtyMainProcessTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}
