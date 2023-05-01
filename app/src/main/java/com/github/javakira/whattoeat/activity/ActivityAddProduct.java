package com.github.javakira.whattoeat.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.javakira.whattoeat.FileIO;
import com.github.javakira.whattoeat.databinding.ActivityAddProductBinding;
import com.github.javakira.whattoeat.model.Eat;
import com.github.javakira.whattoeat.model.Product;
import com.github.javakira.whattoeat.model.ProductType;
import com.github.javakira.whattoeat.model.containers.Products;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;
import java.util.Optional;

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

        launcher = registerForActivityResult(new ActivityResultContract<String, Optional<ProductType>>() {
            @NonNull
            @Override
            public Intent createIntent(@NonNull Context context, String s) {
                return new Intent(context, ActivityListProduct.class);
            }

            @Override
            public Optional<ProductType> parseResult(int i, @Nullable Intent intent) {
                //ToDo temp
                if (intent != null)
                    return Optional.of(new ProductType( intent.getStringExtra("productType"), new Date()));
                else {
                    return Optional.empty();
                }
            }
        }, result -> result.ifPresent(type -> productType = type));

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
                FileIO.changeProducts(binding.getRoot().getContext(),
                        products -> products.list().add(new Product(productType.title, new Date(), count)));
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
