package com.github.javakira.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.github.javakira.whattoeat.FileIO;
import com.github.javakira.whattoeat.adapter.ProductTypeAdapter;
import com.github.javakira.whattoeat.databinding.ActivityListProductBinding;
import com.github.javakira.whattoeat.model.ProductType;
import com.github.javakira.whattoeat.model.containers.ProductTypes;

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

        ProductTypes productTypes = FileIO.productTypes(binding.getRoot().getContext());
        ProductTypeAdapter products = new ProductTypeAdapter(binding.getRoot().getContext(), productTypes.list(), productType -> {
            Intent data = new Intent();
            data.putExtra("productType", productType.title);
            setResult(RESULT_OK, data);
            finish();
        });
        FileIO.store(productTypes, binding.getRoot().getContext());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL, false);
        binding.products.setLayoutManager(layoutManager);
        binding.products.setAdapter(products);

        binding.floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, ActivityAddTypeProduct.class);
            startActivity(intent);
        });
    }
}