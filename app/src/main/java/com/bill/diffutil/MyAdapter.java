package com.bill.diffutil;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    private List<String> data;
    Context context;

    public MyAdapter(Context context,List<String> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("VIEWTYPE",viewType+"");
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        final String cla = data.get(position);
        holder.bindData(cla);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, cla, Toast.LENGTH_SHORT).show();
                data.remove(position);
                notifyDataSetChanged();

            }
        });

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
