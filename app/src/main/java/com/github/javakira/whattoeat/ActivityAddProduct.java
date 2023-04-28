package com.github.javakira.whattoeat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.databinding.ActivityAddProductBinding;
import com.github.javakira.whattoeat.model.Eat;
import com.github.javakira.whattoeat.model.ProductType;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class ActivityAddProduct extends AppCompatActivity {
    private ActivityAddProductBinding binding;

    private ActivityResultLauncher<String> launcher;

    private ProductType productType;
    private int count = 1;

    public ActivityAddProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        launcher = registerForActivityResult(new ActivityResultContract<String, ProductType>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, String s) {
                return new Intent(context, ActivityListProduct.class);
            }

            @Override
            public ProductType parseResult(int i, @Nullable Intent intent) {
                //ToDo temp
                return new ProductType( intent.getStringExtra("productType"), new Date());
            }
        }, this::updateProductType);

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

        binding.endButton.setOnClickListener(view -> {
            if (productType != null) {
                FileIO.addProduct(new Eat(productType.title, new Date(), count), binding.getRoot().getContext());
                finish();
            } else {
                Snackbar.make(view, "Вы не выбрали тип продукта!", BaseTransientBottomBar.LENGTH_LONG).show();
            }
        });

        updateCount();
    }

    private void updateProductType(ProductType productType) {
        this.productType = productType;
        binding.textView9.setText(productType.title);
    }

    private void updateCount() {
        binding.textView8.setText("Количество: " + count);
    }
}
