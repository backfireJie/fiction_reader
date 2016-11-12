package com.example.hhj.fiction_reader.adapter;

import java.util.List;

/**
 * Created by XX on 2016/10/13.
 */
public abstract  class SimpleAdapter<T> extends BasicAdapter<T,BasicViewHolder>{

    public SimpleAdapter(int layoutResId,List<T> datas) {
        super(layoutResId, datas);
    }
}
