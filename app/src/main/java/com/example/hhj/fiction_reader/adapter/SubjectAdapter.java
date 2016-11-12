package com.example.hhj.fiction_reader.adapter;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.BasicBean;

import java.util.List;

/**
 * Created by backfire on 2016/10/15.
 */
public class SubjectAdapter extends  SimpleAdapter<BasicBean> {

    public SubjectAdapter( List<BasicBean> datas) {
        super(R.layout.template_subject, datas);
    }
    @Override
    protected void bound(BasicViewHolder viewHolder, BasicBean item) {

    }
}
