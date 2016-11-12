package com.example.hhj.fiction_reader.adapter;

import android.widget.TextView;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by XX on 2016/10/13.
 */
public class MasterAdapter extends  SimpleAdapter<BasicBean>{
    private SimpleDraweeView sdvMaster;
    private TextView tvTitle;
    private TextView tvBrief;
    private TextView tvTime;


    public MasterAdapter( List<BasicBean> datas) {
        super(R.layout.template_master_cardview, datas);
    }

    @Override
    protected void bound(BasicViewHolder viewHolder, BasicBean item) {

        sdvMaster = (SimpleDraweeView) viewHolder.myFindView(R.id.sdv_master_card);
        tvTitle = (TextView) viewHolder.myFindView(R.id.tv_master_title_card);
        tvBrief =(TextView) viewHolder.myFindView(R.id.tv_master_brief_card);
        tvTime = (TextView)viewHolder.myFindView(R.id.tv_master_time_card);
        sdvMaster.setImageURI(item.getImgUrl());
        tvTitle.setText(item.getTitle());
        tvBrief.setText(item.getBrief()+"......");
        tvTime.setText(item.getTime());


    }
}
