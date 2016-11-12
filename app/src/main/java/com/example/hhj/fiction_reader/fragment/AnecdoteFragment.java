package com.example.hhj.fiction_reader.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.adapter.AnecdoteAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.web_data.GetAnecdoteDatas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/9.
 */
public class AnecdoteFragment extends Fragment {
    private RecyclerView rvAnecdote;
    private MaterialRefreshLayout refreshAnecdote;
    private GetAnecdoteDatas getAnecdoteDatas;
    private List<BasicBean> anecdoteList;
    private AnecdoteAdapter mAdapter;
    //加载数据
    private Document document;
    private static final int STATE_NORMAL = 0;
    private static final int  STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;
    private int currentURLNum = 2 ;
    private String currentURL ="http://www.wcsfa.com/scfbox_list-2-4.html";



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anecodte,container,false);
        initView(view);
        initRefreshLayout();
        getAnecdoteDatas();


        return  view;
    }

    private void initView(View view) {
        rvAnecdote =(RecyclerView) view.findViewById(R.id.rv_anecodte);
        refreshAnecdote =(MaterialRefreshLayout) view.findViewById(R.id.refresh_anecodte);
    }
    private void initRefreshLayout(){

    }
    private void getAnecdoteDatas(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                anecdoteList = new ArrayList<>();
                try{
                    document = Jsoup.connect(currentURL).timeout(10000).get();

                }catch(IOException e){
                    e.printStackTrace();
                }

                getAnecdoteDatas= new GetAnecdoteDatas();
                anecdoteList=getAnecdoteDatas.getAnecodoteList(document);
                handler.sendEmptyMessage(1);

            }
        }).start();


    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            showDatas(anecdoteList);

        }
    };

    private void showDatas(List<BasicBean> anecdoteList){
        System.out.println("showData有执行");

        mAdapter = new AnecdoteAdapter(anecdoteList);
        rvAnecdote.setAdapter(mAdapter);
        rvAnecdote.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvAnecdote.setItemAnimator(new DefaultItemAnimator());
    }


}
