package com.example.hhj.fiction_reader.adapter;

import android.widget.TextView;

import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by XX on 2016/10/13.
 */
public class AnecdoteAdapter  extends SimpleAdapter<BasicBean>{
    private SimpleDraweeView sdvAnecdote;
    private TextView tvAnecdoteTitle;
    private TextView tvAnecdoteBrief;
    private TextView tvAnecdoteTime;


    public AnecdoteAdapter( List<BasicBean> datas) {
        super(R.layout.template_anecdote, datas);
    }

    @Override
    protected void bound(BasicViewHolder viewHolder, BasicBean item) {


        tvAnecdoteTitle = (TextView) viewHolder.myFindView(R.id.tv_anecdote_title);
        tvAnecdoteBrief = (TextView) viewHolder.myFindView(R.id.tv_anecdote_brief);
        tvAnecdoteTime= (TextView) viewHolder.myFindView(R.id.tv_anecdote_time);

        tvAnecdoteTitle.setText(item.getTitle());
        tvAnecdoteBrief.setText(item.getBrief());

    }
}
