package com.example.hhj.fiction_reader.adapter;

import android.widget.TextView;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by backfire on 2016/10/15.
 */
public class OtherAdapter extends  SimpleAdapter<BasicBean> {
    private SimpleDraweeView sdvOther;
    private TextView tvTitle;
    private TextView tvBrief;
    private TextView tvTime;

    public OtherAdapter(List<BasicBean> datas) {
        super(R.layout.template_other, datas);
    }
    @Override
    protected void bound(BasicViewHolder viewHolder, BasicBean item) {
        sdvOther= (SimpleDraweeView) viewHolder.myFindView(R.id.sdv_other);
        tvTitle = (TextView) viewHolder.myFindView(R.id.tv_other_title);
        tvBrief =(TextView) viewHolder.myFindView(R.id.tv_other_brief);

        sdvOther.setImageURI(item.getImgUrl());
        tvTitle.setText(item.getTitle());
        tvBrief.setText(item.getBrief());
    }
}
