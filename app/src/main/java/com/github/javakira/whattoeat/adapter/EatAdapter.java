package com.github.javakira.whattoeat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.javakira.whattoeat.databinding.ItemCardBinding;
import com.github.javakira.whattoeat.model.Eat;

import java.util.List;

public class EatAdapter extends RecyclerView.Adapter<EatAdapter.EatViewHolder> {
    private final Context context;
    private final List<Eat> eats;

    public EatAdapter(Context context, List<Eat> eats) {
        this.context = context;
        this.eats = eats;
    }

    @NonNull
    @Override
    public EatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardBinding binding = ItemCardBinding.inflate(LayoutInflater.from(context), parent, false);
        return new EatViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EatViewHolder holder, int position) {
        holder.visit(eats.get(position));
    }

    @Override
    public int getItemCount() {
        return eats.size();
    }

    public static class EatViewHolder extends RecyclerView.ViewHolder {
        private final ItemCardBinding binding;

        public EatViewHolder(@NonNull View itemView, ItemCardBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void visit(Eat eat) {
            binding.textView3.setText(eat.title);
            binding.textView4.setText("испортится через" + eat.spoil);
            binding.textView5.setText(eat.count + " штук");
        }
    }
}
