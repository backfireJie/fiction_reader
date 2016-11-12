package com.example.hhj.fiction_reader.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by XX on 2016/10/13.
 */
public class BasicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected SparseArray<View> views;
    private BasicAdapter.MyOnBasicClickListener mListener;

    public BasicViewHolder(View itemView,BasicAdapter.MyOnBasicClickListener mListener) {
        super(itemView);
        itemView.setOnClickListener(this);
        this.mListener = mListener;
        this.views = new SparseArray<View>();
    }
    protected <T extends View> T myFindView(int viewId){
        View view = views.get(viewId);
        if(view == null){
            view = itemView.findViewById(viewId);
            views.put(viewId,view);
        }
        return (T)view;
    }


    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,getLayoutPosition());
        }
    }
}
