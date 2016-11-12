package com.example.hhj.fiction_reader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.hhj.fiction_reader.ArticleDetailListViewActivity;
import com.example.hhj.fiction_reader.MyStaticDatas;
import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.adapter.BasicAdapter;
import com.example.hhj.fiction_reader.adapter.MasterAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.web_data.GetBasicDatas;
import com.example.hhj.fiction_reader.web_data.GetMasterDatas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/9.
 */
public class MasterpiecesFragment extends Fragment {
    private MaterialRefreshLayout refreshMaster;
    private RecyclerView rvMaster;
    private List<BasicBean> basicBeanList;
    private GetBasicDatas getMasterDatas;
    private MasterAdapter masterAdapter;
    //加载数据
    private Document document;
    private int currentURLNum = 1;
    private final static int currentMinNum = 1;
    private String currentURL = "http://www.wcsfa.com/scfbox_list-" + currentURLNum + "-2.html";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_master, container, false);
        refreshMaster =(MaterialRefreshLayout) view.findViewById(R.id.refresh_master);
        initRefreshLayout();
        initView(view);
        getMasterDatas();


        return view;

    }

    public void initView(View view) {
        rvMaster = (RecyclerView) view.findViewById(R.id.rv_master);
    }

    private void getMasterDatas() {
        if(basicBeanList != null){
            basicBeanList.clear();
        }
        basicBeanList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    document = Jsoup.connect(currentURL).timeout(10000).get();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                getMasterDatas = new GetMasterDatas();
                basicBeanList = getMasterDatas.getBasicBeanList(document);
                handler.sendEmptyMessage(1);

            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            showDatas(basicBeanList);
        }
    };

    public void showDatas(final List<BasicBean> list) {

        masterAdapter = new MasterAdapter(list);
        masterAdapter.setOnBasicClickListener(new BasicAdapter.MyOnBasicClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = new Intent(getActivity(), ArticleDetailListViewActivity.class);
                String theCommentURL = list.get(position).getTextUrl();
                String theTitle = list.get(position).getTitle();
                intent.putExtra(MyStaticDatas.TheCommentURL, theCommentURL);
                intent.putExtra(MyStaticDatas.TheTitle, theTitle);
                intent.putExtra(MyStaticDatas.ComeFromWhere,MyStaticDatas.FromMain);
                startActivity(intent);
            }
        });
        final GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        rvMaster.setLayoutManager(manager);
        rvMaster.setAdapter(masterAdapter);
        rvMaster.setItemAnimator(new DefaultItemAnimator());
        rvMaster.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){


                    int lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
                    if (lastVisiblePosition >= manager.getItemCount() - 1 && lastVisiblePosition > 0) {

                        Log.i("MYTAG","到底"+lastVisiblePosition+"manage.count"+manager.getItemCount());


                        getMoreDatas();

                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }



    public void getMoreDatas() {

        currentURLNum = currentURLNum + 1;
        currentURL = "http://www.wcsfa.com/scfbox_list-" + currentURLNum + "-2.html";
        getMasterDatas();


    }


    public void getLastPageDatas() {
        if (currentURLNum > currentMinNum) {
            currentURLNum = currentURLNum - 1;
            currentURL = "http://www.wcsfa.com/scfbox_list-" + currentURLNum + "-2.html";
            Toast.makeText(getContext(),"当前页"+currentURLNum,Toast.LENGTH_SHORT).show();
            getMasterDatas();

        }
    }
    private void initRefreshLayout(){

        refreshMaster.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                // getRefreshDatas();
                getLastPageDatas();
                refreshMaster.finishRefresh();

            }


        });
    }

}






