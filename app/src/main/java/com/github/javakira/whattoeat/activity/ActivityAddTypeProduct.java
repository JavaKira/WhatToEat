package com.github.javakira.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.javakira.whattoeat.FileIO;
import com.github.javakira.whattoeat.R;
import com.github.javakira.whattoeat.databinding.ActivityAddTypeProductBinding;
import com.github.javakira.whattoeat.model.ProductType;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class ActivityAddTypeProduct extends AppCompatActivity {
    private ActivityAddTypeProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddTypeProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.endButton.setOnClickListener(view -> {
            if (!binding.editTitle.getText().toString().isEmpty()) {
                FileIO.changeProductTypes(binding.getRoot().getContext(), products -> {
                    products.list().add(new ProductType(binding.editTitle.getText().toString(), new Date()));
                });

                finish();
            } else {
                Snackbar.make(view, "Вы не ввели название продукта!", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });
    }
}