package com.example.hhj.fiction_reader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hhj.fiction_reader.R;

/**
 * Created by XX on 2016/10/12.
 */
public class MineFragment  extends Fragment{
    private ListView lvClassify;
    private String[] classify ;
    private ArrayAdapter<String> leftAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        initView(view);
        return  view;

    }

    private void initView(View view) {
        classify = new String[]{"佳作","幻闻","专题","评论","其他","我的"};
        lvClassify = (ListView) view.findViewById(R.id.lv_left_classify);
        showLeftView();

    }
    private void showLeftView(){
        leftAdapter = new ArrayAdapter<String>(getActivity(),R.layout.simple_classify,classify);
        lvClassify.setAdapter(leftAdapter);

    }

}
