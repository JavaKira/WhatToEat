package com.github.javakira.whattoeat.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.databinding.ActivityAddDishesBinding;

public class ActivityAddDishes extends AppCompatActivity {
    private ActivityAddDishesBinding binding;

    public ActivityAddDishes() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddDishesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
