package com.example.hhj.fiction_reader.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.example.hhj.fiction_reader.adapter.FutureAllAdapter;
import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.http.OkHttpManager;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by backfire on 2016/10/22.
 */
public class FutureAllFragment extends Fragment {

    private RecyclerView rvFutureAll;
    private MaterialRefreshLayout refreshFutureAll;
    private FutureAllAdapter futureAllAdapter;
    private final static int currentMinNum = 1;

    private int currentURLNum = 1;
    private String currentURL = "http://www.wcsfa.com/wlkhds.php?sort=sort_order&page="+currentURLNum+"";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.future_tab_item_all,null);
        initView(view);
        getAllData();
        initRefreshLayout();
        return  view;
    }

    private void initView(View view) {

        rvFutureAll = (RecyclerView) view.findViewById(R.id.rv_future_all);
        refreshFutureAll =(MaterialRefreshLayout) view.findViewById(R.id.refresh_future_all);
    }


    private void getAllData() {

       final List<BasicBean> basicBeanList = new ArrayList<>();
        OkHttpManager httpManager = new OkHttpManager();
        httpManager.getAsyn(currentURL, new OkHttpManager.DataRequestResponse() {
            @Override
            public void onDataResult(String htmlData) {
                BasicBean basicBean;
                Document document = Jsoup.parse(htmlData);
                Elements futureElements = document.select("div.list").select("div.left").select("ul").select("li");

                for(Element element : futureElements){
                    String imgUrl = element.select("a").select("img").attr("src");
                    String textUrl = element.select("a").attr("href");
                    String title = element.select("a").select("p.tit").text();
                    String name = element.select("a").select("p.name").text();
                    String brief =element.select("p").select("font").text();
                    String titAndName = title+"-"+name;
                    basicBean = new BasicBean(MyStaticDatas.ABSImg+imgUrl,MyStaticDatas.ABSText+textUrl,titAndName,brief);
                    basicBeanList.add(basicBean);


                }

                getActivity().runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        futureAllAdapter = new FutureAllAdapter(basicBeanList);
                        futureAllAdapter.setOnBasicClickListener(new BasicAdapter.MyOnBasicClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(getContext(), ArticleDetailListViewActivity.class);
                                intent.putExtra(MyStaticDatas.TheCommentURL,basicBeanList.get(position).getTextUrl());
                                intent.putExtra(MyStaticDatas.ComeFromWhere,MyStaticDatas.FromFuture);
                                startActivity(intent);
                            }
                        });
                        rvFutureAll.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                            @Override
                            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                            }
                        });

                        final GridLayoutManager manager = new GridLayoutManager(getActivity(),2);

                        rvFutureAll .setLayoutManager(manager);
                        rvFutureAll .setAdapter(futureAllAdapter);
                        rvFutureAll.setItemAnimator(new DefaultItemAnimator());
                        rvFutureAll.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if(newState == RecyclerView.SCROLL_STATE_IDLE){

                                    int lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
                                    //lastVisiblePositon > 0
                                    if (lastVisiblePosition >= manager.getItemCount() - 1 && lastVisiblePosition > 0) {

                                        //Log.i("MYTAG","到底"+lastVisiblePosition+"manage.count"+manager.getItemCount());
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
                });
            }

            @Override
            public void onDataFailure(IOException e) {

            }
        });
    }


    private void initRefreshLayout(){

        refreshFutureAll.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getLastPageDatas();
            }


        });
    }
    private void getMoreDatas() {

        currentURLNum++;
        Toast.makeText(getContext(),"当前页"+currentURLNum,Toast.LENGTH_SHORT).show();
        currentURL = "http://www.wcsfa.com/wlkhds.php?sort=sort_order&page=" +currentURLNum+ "";
        getAllData();




    }


    private void getLastPageDatas(){
        if(currentURLNum > currentMinNum){
            currentURLNum--;

        }

        currentURL =  "http://www.wcsfa.com/wlkhds.php?sort=sort_order&page="+currentURLNum+"";
        refreshFutureAll.finishRefresh();
        getAllData();
    }




}
