package com.example.hhj.fiction_reader.adapter;

import android.widget.TextView;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by XX on 2016/10/19.
 */
public class FutureAllAdapter extends  SimpleAdapter<BasicBean>{
    private SimpleDraweeView sdvFuture;
    private TextView tvTitle;
    private TextView tvBrief;
    public FutureAllAdapter(List<BasicBean> list){
        super(R.layout.template_future_all_cardview,list);
    }
    @Override
    protected void bound(BasicViewHolder viewHolder, BasicBean item) {
        sdvFuture = viewHolder.myFindView(R.id.sdv_future);
        tvTitle = viewHolder.myFindView(R.id.tv_future_title_card);
        tvBrief = viewHolder.myFindView(R.id.tv_future_brief_card);

        sdvFuture.setImageURI(item.getImgUrl());
        tvBrief.setText(item.getBrief());
        tvTitle.setText(item.getTitle());
    }
}
