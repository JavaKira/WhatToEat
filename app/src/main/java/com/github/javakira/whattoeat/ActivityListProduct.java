package com.github.javakira.whattoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.javakira.whattoeat.databinding.ActivityListProductBinding;

public class ActivityListProduct extends AppCompatActivity {
    private ActivityListProductBinding binding;

    public ActivityListProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}