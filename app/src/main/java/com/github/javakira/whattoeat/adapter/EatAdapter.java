package com.github.javakira.whattoeat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.github.javakira.whattoeat.databinding.ItemCardBinding;
import com.github.javakira.whattoeat.model.Eat;

import java.util.List;

public class EatAdapter extends ModelAdapter<Eat, EatAdapter.EatViewHolder> {

    public EatAdapter(Context context, List<Eat> eats) {
        super(context, eats);
    }

    @NonNull
    @Override
    public EatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(context), parent, false);
        return new EatViewHolder(binding.getRoot(), binding);
    }


    public static class EatViewHolder extends ModelViewHolder<Eat> {
        private final ItemCardBinding binding;

        public EatViewHolder(@NonNull View itemView, ItemCardBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        @Override
        public void visit(Eat eat) {
            binding.textView3.setText(eat.title);
            binding.textView4.setText("испортится через" + eat.spoil);
            binding.textView5.setText(eat.count + " штук");
        }
    }
}
