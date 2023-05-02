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

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
            FileIO.changeProducts(holder.binding.getRoot().getContext(),
                    products -> products.list().remove(product));
            if (product.count > 1)
                FileIO.changeProducts(holder.binding.getRoot().getContext(),
                        products -> products.list().add(new Product(product.title, product.spoil, product.count - 1)));
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
            long diff = product.spoil.getTime() - new Date().getTime();
            binding.textView4.setText("испортится через " + diff/(1000*3600*24) + " дней");
            binding.textView5.setText(product.count + " штук");
        }
    }
}
