package com.example.hhj.fiction_reader.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by XX on 2016/10/12.
 */
public abstract class BasicAdapter<T,H extends BasicViewHolder> extends RecyclerView.Adapter<BasicViewHolder>{
    //幻闻、评论、其他的父类适配器
    protected Context mContext;
    protected int layoutResId;
    protected List<T> datas;



    public BasicAdapter(int layoutResId,List<T> datas){
        this.layoutResId = layoutResId;
        this.datas = datas;
    }


    //定义监听事件
    private MyOnBasicClickListener mListener = null;
    public void setOnBasicClickListener(MyOnBasicClickListener mListener){
        this.mListener = mListener;
    }
    public interface MyOnBasicClickListener{
        void onItemClick(View view, int position);
    }

    //重写方法
    @Override
    public BasicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(layoutResId,parent,false);
        BasicViewHolder vh = new BasicViewHolder(view,mListener);
        return  vh;
    }

    @Override
    public void onBindViewHolder(BasicViewHolder holder, int position) {
        T item = getItem(position);
        bound((H)holder,item);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    //一些共用的方法
    public void clear(){
        datas.clear();
        notifyItemRangeChanged(0,datas.size());

    }

    public T getItem(int position){
        if(position >= datas.size()){
            return  null;
        }
        return  datas.get(position);
    }
    public List<T> getDatas(){
        return datas;
    }

    public void addData(List<T> datas){
        addData(0,datas);
    }

    public void addData(int position,List<T> list){
        if(list != null && list.size()>0){
            datas.addAll(list);
            notifyItemInserted(position);
        }
    }

    //由子类绑定数据
    protected  abstract void bound(H viewHolder,T item);







}

