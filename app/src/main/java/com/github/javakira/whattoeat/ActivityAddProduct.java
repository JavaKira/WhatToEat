package com.github.javakira.whattoeat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.databinding.ActivityAddProductBinding;

public class ActivityAddProduct extends AppCompatActivity {
    private ActivityAddProductBinding binding;

    private int count = 1;

    public ActivityAddProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.amountAdd.setOnClickListener(view -> {
            count++;
            updateCount();
        });

        binding.amountRem.setOnClickListener(view -> {
            if (count > 1) {
                count--;
                updateCount();
            }
        });

        binding.productEdit.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityListProduct.class);
            startActivity(intent);
        });

        updateCount();
    }

    private void updateCount() {
        binding.textView8.setText("Количество: " + count);
    }
}
