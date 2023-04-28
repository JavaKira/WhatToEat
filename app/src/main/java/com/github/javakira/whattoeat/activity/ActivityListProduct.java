package com.github.javakira.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.javakira.whattoeat.adapter.ProductTypeAdapter;
import com.github.javakira.whattoeat.databinding.ActivityListProductBinding;
import com.github.javakira.whattoeat.model.ProductType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityListProduct extends AppCompatActivity {
    private ActivityListProductBinding binding;

    public ActivityListProduct() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityListProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<ProductType> productsList = new ArrayList<>();
        productsList.add(new ProductType("Яица", new Date()));
        ProductTypeAdapter products = new ProductTypeAdapter(binding.getRoot().getContext(), productsList, productType -> {
            Intent data = new Intent();
            data.putExtra("productType", productType.title);
            setResult(RESULT_OK, data);
            finish();
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL, false);
        binding.products.setLayoutManager(layoutManager);
        binding.products.setAdapter(products);
    }
}