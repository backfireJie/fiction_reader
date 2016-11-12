package com.example.hhj.fiction_reader.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.example.hhj.fiction_reader.MyStaticDatas;
import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.adapter.AnecdoteAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by backfire on 2016/10/22.
 */
public class FutureHotFragment extends Fragment {

    private RecyclerView rvFutureHot;
    private MaterialRefreshLayout refresh;
    private AnecdoteAdapter hotAdapter;
    private int currentURLNum = 2;
    private String currentURL =  "http://www.wcsfa.com/wlkhds.php?sort=sort_order&page="+currentURLNum+"";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_anecodte,container,false);
        initView(view);
        getHotDatas();
        return  view;
    }

    private void getHotDatas() {
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(currentURL)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                BasicBean basicBean;
                final List<BasicBean> basicBeanList = new ArrayList<BasicBean>();
                Document document = Jsoup.parse(response.body().string());
                System.out.println("document"+document);
                Elements hotElements = document.select("div.list").select("div.right").select("ul").select("li");

                for(Element element : hotElements){
                    String textUrl = element.select("a").attr("href");
                    String  title= element.select("a").text();
                    basicBean = new BasicBean(null, MyStaticDatas.ABSText+textUrl,title,null);
                    basicBeanList.add(basicBean);

                }

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hotAdapter = new AnecdoteAdapter(basicBeanList);
                        rvFutureHot.setAdapter(hotAdapter);
                        rvFutureHot.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rvFutureHot.setItemAnimator(new DefaultItemAnimator());

                    }
                });

            }
        });
    }

    private void initView(View view) {
        rvFutureHot = (RecyclerView) view.findViewById(R.id.rv_anecodte);
        refresh =(MaterialRefreshLayout)view.findViewById(R.id.refresh_anecodte);
    }


}
