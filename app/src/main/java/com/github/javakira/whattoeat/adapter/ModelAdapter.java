package com.github.javakira.whattoeat.adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class ModelAdapter<T> extends RecyclerView.Adapter<ModelAdapter.ModelViewHolder<T>>{
    protected final Context context;
    protected final List<T> models;

    public ModelAdapter(Context context, List<T> models) {
        this.context = context;
        this.models = models;
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ModelViewHolder holder, int position) {
        holder.visit(models.get(position));
    }

    public abstract static class ModelViewHolder<T> extends RecyclerView.ViewHolder {
        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public abstract void visit(T model);
    }
}
