package com.bill.diffutil;

import android.util.Log;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class MyDiffUtilsCallBack extends DiffUtil.Callback {

    private List<String> oldList;
    private List<String> newList;

    public MyDiffUtilsCallBack(List<String> oldList, List<String> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        if (oldList.get(oldItemPosition).equals(newList.get(newItemPosition))){
            Log.e("SAME","SAME");
        }
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (oldList.get(oldItemPosition) == newList.get(newItemPosition)){
            Log.e("SAME1","SAME1");
        }
        return oldList.get(oldItemPosition) == newList.get(newItemPosition);
    }
}
