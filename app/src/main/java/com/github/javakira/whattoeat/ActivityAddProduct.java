package com.github.javakira.whattoeat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.databinding.ActivityAddProductBinding;

public class ActivityAddProduct extends AppCompatActivity {
    private ActivityAddProductBinding binding;

    private ActivityResultLauncher<String> launcher;

    private int count = 1;

    public ActivityAddProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContract<String, String>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, String s) {
                return new Intent(context, ActivityListProduct.class);
            }

            @Override
            public String parseResult(int i, @Nullable Intent intent) {
                return intent.getStringExtra("productType");
            }
        }, result -> {
            binding.textView9.setText(result);
        });

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
            launcher.launch("");
        });

        updateCount();
    }

    private void updateCount() {
        binding.textView8.setText("Количество: " + count);
    }
}
