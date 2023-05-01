package com.github.javakira.whattoeat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.javakira.whattoeat.FileIO;
import com.github.javakira.whattoeat.databinding.ItemCardBinding;
import com.github.javakira.whattoeat.model.Eat;
import com.github.javakira.whattoeat.model.Product;

import java.util.List;

public class ProductAdapter extends ModelAdapter<Product, ProductAdapter.ProductViewHolder> {

    public ProductAdapter(Context context, List<Product> products) {
        super(context, products);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.binding.getRoot().setOnClickListener(view -> {
            Product product = models.get(position);
            FileIO.remProduct(product, holder.binding.getRoot().getContext());
            if (product.count > 1)
                FileIO.addProduct(new Product(product.title, product.spoil, product.count - 1),
                        holder.binding.getRoot().getContext());
        });
    }


    public static class ProductViewHolder extends ModelViewHolder<Product> {
        private final ItemCardBinding binding;

        public ProductViewHolder(@NonNull View itemView, ItemCardBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @Override
        public void visit(Product product) {
            binding.textView3.setText(product.title);
            binding.textView4.setText("испортится через" + product.spoil);
            binding.textView5.setText(product.count + " штук");
        }
    }
}
