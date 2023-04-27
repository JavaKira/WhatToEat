package com.github.javakira.whattoeat;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.databinding.ActivityAddProductBinding;

public class ActivityAddProduct extends AppCompatActivity {
    private ActivityAddProductBinding binding;

    public ActivityAddProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
