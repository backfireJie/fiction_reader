package com.example.hhj.fiction_reader.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.hhj.fiction_reader.ArticleDetailActivity;
import com.example.hhj.fiction_reader.MyStaticDatas;
import com.example.hhj.fiction_reader.adapter.BasicAdapter;
import com.example.hhj.fiction_reader.adapter.MasterAdapter;
import com.example.hhj.fiction_reader.adapter.SimpleAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.web_data.GetBasicDatas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 废弃类  复用性差
 * Created by XX on 2016/10/14.
 */
public class BasicMoreAndRefresh {

    private List<BasicBean> basicBeanList ;

    private Context mContext;
    private MaterialRefreshLayout mRefresh;
    private SimpleAdapter mAdapter ;
    private RecyclerView mRView;
    private GetBasicDatas getBasicDatas;

    private int currentURLNum;
    private String currentURL;
    private static final int currentMinNum = 2;

    private Document document;



    public BasicMoreAndRefresh(Context mContext,MaterialRefreshLayout mRefresh,RecyclerView mRView,
           GetBasicDatas getBasicDatas,int currentURLNum,String currentURL){
        this.mContext = mContext;
        this.mRefresh = mRefresh;
        this.mRView = mRView;

        this.getBasicDatas = getBasicDatas;
        this.currentURLNum = currentURLNum;
        this.currentURL = currentURL;
        initRefreshLayout();
        getBasicDatas();
    }



    public void initRefreshLayout(){
        mRefresh.setLoadMore(true);
        mRefresh.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getLastPageDatas();
                mRefresh.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                getMoreDatas();
                mRefresh.finishRefreshLoadMore();
            }
        });

    }
    //获取单页数据

    private void getBasicDatas() {
        if(basicBeanList != null){
            basicBeanList = new ArrayList<>();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                    document = Jsoup.connect(currentURL).timeout(10000).get();

                    //System.out.println(commentBeanList.get(1).getBrief()+","+commentBeanList.get(1).getImgUrl());

                }catch(IOException e){
                    e.printStackTrace();
                }
                if(document != null){

                    basicBeanList =getBasicDatas.getBasicBeanList(document);
                    //System.out.println("可以"+basicBeanList.get(0).getImgUrl()+","+basicBeanList.get(0).getBrief());

                }else{
                    Toast.makeText(mContext,"无更多数据",Toast.LENGTH_SHORT).show();
                }

                // handler.obtainMessage(1,commentBeanList);
                handler.sendEmptyMessage(1);
            }
        }).start();

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            showDataAtTime(basicBeanList);
        }
    };

    private void showDataAtTime(final List<BasicBean> list){
        //mAdapter要传入进来，并重新使用构造函数。这样才能复用
        final BasicAdapter mAdapter = new MasterAdapter(list);

        mRView.setAdapter(mAdapter);
        mAdapter.setOnBasicClickListener(new BasicAdapter.MyOnBasicClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                String textURL = list.get(position).getTextUrl();
                intent.putExtra(MyStaticDatas.TheCommentURL,textURL);
                mContext.startActivity(intent);

            }
        });



        mRView.setLayoutManager(new LinearLayoutManager(mContext));
        mRView.setItemAnimator(new DefaultItemAnimator());

    }
    public void getMoreDatas(){


        currentURLNum  = currentURLNum + 1;
        //重新赋值有问题  不能复用
        currentURL= "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-5.html";
        getBasicDatas();

    }
    public void getLastPageDatas(){
        if(currentURLNum > currentMinNum){

            currentURLNum = currentURLNum - 1;
            currentURL= "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-5.html";
            getBasicDatas();

        }
    }
}

