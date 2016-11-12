package com.example.hhj.fiction_reader.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.adapter.OtherAdapter;
import com.example.hhj.fiction_reader.adapter.SimpleAdapter;
import com.example.hhj.fiction_reader.adapter.SubjectAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.web_data.GetBasicDatas;
import com.example.hhj.fiction_reader.web_data.GetOtherDatas;
import com.example.hhj.fiction_reader.web_data.GetSubjectDatas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/9.
 */
public class OtherFragment extends Fragment {
    private MaterialRefreshLayout otherRefresh;
    private RecyclerView otherRView;
    private List<BasicBean> otherList;
    private Context context;
    private GetBasicDatas getOtherDatas;
    private int currentURLNum ;
    private String currentURL;


    private SimpleAdapter otherAdapter ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other,container,false);
        initView(view);
        initData();
        return  view;

    }



    private void initView(View view) {
        otherRefresh = (MaterialRefreshLayout) view.findViewById(R.id.refresh_other);
        otherRView = (RecyclerView) view.findViewById(R.id.rv_other);
    }

    private void initData() {

        context = getActivity();
        otherList = new ArrayList<BasicBean>();
        getOtherDatas = new GetOtherDatas();
        currentURLNum = 0;
        currentURL= "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-5.html";
        //Context mContext,MaterialRefreshLayout mRefresh,RecyclerView mRView,SimpleAdapter mAdapter,
        //GetBasicDatas getBasicDatas,int currentURLNum,String currentURL
        BasicMoreAndRefresh basicMoreAndRefresh
                = new BasicMoreAndRefresh(context,otherRefresh,otherRView,getOtherDatas,currentURLNum,currentURL);

    }
}
