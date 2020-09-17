package com.bill.diffutil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<String> data;

    public MyAdapter(List<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.bindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.tv_name);
        }

        void bindData(String s) {
            tvNama.setText(s);
        }
    }

    public void insertData(List<String> newData){
        MyDiffUtilsCallBack diffUtilsCallBack = new MyDiffUtilsCallBack(newData, data);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilsCallBack);
        data.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    public void updateData(List<String> newData){
        MyDiffUtilsCallBack diffUtilsCallBack = new MyDiffUtilsCallBack(data, newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilsCallBack);

        data.clear();
        data.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }
}
