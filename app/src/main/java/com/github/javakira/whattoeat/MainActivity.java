package com.github.javakira.whattoeat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.github.javakira.whattoeat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                setDishes();
            else
                setProducts();
        });
    }

    private void setProducts() {
        binding.dishes.setVisibility(View.GONE);
        binding.products.setVisibility(View.VISIBLE);
    }

    private void setDishes() {
        binding.dishes.setVisibility(View.VISIBLE);
        binding.products.setVisibility(View.GONE);
    }
}