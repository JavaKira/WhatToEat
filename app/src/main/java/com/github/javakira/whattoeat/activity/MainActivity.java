package com.github.javakira.whattoeat.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.github.javakira.whattoeat.FileIO;
import com.github.javakira.whattoeat.adapter.EatAdapter;
import com.github.javakira.whattoeat.adapter.ProductAdapter;
import com.github.javakira.whattoeat.databinding.ActivityMainBinding;
import com.github.javakira.whattoeat.model.Eat;
import com.github.javakira.whattoeat.model.Product;
import com.github.javakira.whattoeat.model.containers.Products;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean isProducts;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Products products = FileIO.products(binding.getRoot().getContext());
        ProductAdapter productsAdapter = new ProductAdapter(binding.getRoot().getContext(), products.list());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL, false);
        binding.products.setLayoutManager(layoutManager);
        binding.products.setAdapter(productsAdapter);

        FileIO.addProductStoreListener(() -> {
            products.list().clear();
            products.list().addAll(FileIO.products(binding.getRoot().getContext()).list());
            productsAdapter.notifyDataSetChanged();
        });

        List<Eat> dishesList = new ArrayList<>();
        dishesList.add(new Eat("Омлет", new Date(), 1));
        EatAdapter dishes = new EatAdapter(binding.getRoot().getContext(), dishesList);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL, false);
        binding.dishes.setLayoutManager(layoutManager1);
        binding.dishes.setAdapter(dishes);

        binding.switch1.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                setDishes();
            else
                setProducts();
        });

        binding.floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, isProducts ? ActivityAddProduct.class : ActivityAddDishes.class);
            startActivity(intent);
        });


        setProducts();
    }

    private void setProducts() {
        binding.dishes.setVisibility(View.GONE);
        binding.products.setVisibility(View.VISIBLE);
        isProducts = true;
    }

    private void setDishes() {
        binding.dishes.setVisibility(View.VISIBLE);
        binding.products.setVisibility(View.GONE);
        isProducts = false;
    }
}