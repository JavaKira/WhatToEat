package com.github.javakira.whattoeat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.javakira.whattoeat.databinding.ChooseProductBinding;
import com.github.javakira.whattoeat.model.ProductType;

import java.util.List;
import java.util.function.Consumer;

public class ProductTypeAdapter extends ModelAdapter<ProductType, ProductTypeAdapter.ProductTypeViewHolder> {
    private final Consumer<ProductType> onClick;

    public ProductTypeAdapter(Context context, List<ProductType> models, Consumer<ProductType> onClick) {
        super(context, models);
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ProductTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ChooseProductBinding binding = ChooseProductBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ProductTypeAdapter.ProductTypeViewHolder(binding.getRoot(), binding);
    }

    /**
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ProductTypeViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.binding.getRoot().setOnClickListener(view -> onClick.accept(models.get(position)));
    }

    public static class ProductTypeViewHolder extends ModelViewHolder<ProductType> {
        private final ChooseProductBinding binding;

        public ProductTypeViewHolder(@NonNull View itemView, ChooseProductBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @Override
        public void visit(ProductType model) {
            binding.textView3.setText(model.title);
        }
    }
}
