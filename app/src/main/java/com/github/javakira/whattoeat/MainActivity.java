package com.github.javakira.whattoeat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.github.javakira.whattoeat.adapter.EatAdapter;
import com.github.javakira.whattoeat.databinding.ActivityMainBinding;
import com.github.javakira.whattoeat.model.Eat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Eat> productsList = new ArrayList<>();
        productsList.add(new Eat("Яица", new Date(), 10));
        EatAdapter products = new EatAdapter(binding.getRoot().getContext(), productsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(binding.getRoot().getContext(), RecyclerView.VERTICAL, false);
        binding.products.setLayoutManager(layoutManager);
        binding.products.setAdapter(products);

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