package com.example.hhj.fiction_reader.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.hhj.fiction_reader.ArticleDetailActivity;
import com.example.hhj.fiction_reader.MyStaticDatas;
import com.example.hhj.fiction_reader.R;
import com.example.hhj.fiction_reader.adapter.CommentAdapter;
import com.example.hhj.fiction_reader.bean.CommentBean;
import com.example.hhj.fiction_reader.web_data.TestGetComment;
import com.example.hhj.fiction_reader.widget.MyToolBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/9.
 */
public class CommentFragment extends Fragment{

   private List<CommentBean> commentBeanList  ;
    private MaterialRefreshLayout refreshComment;

    private RecyclerView rvComment;
    private  Document document;
    private CommentAdapter commentAdapter;

    private TestGetComment testGetComment  = new TestGetComment();

    private MyToolBar myToolBar;
    //下拉加载上拉更新
    private static final int STATE_NORMAL = 0;
    private static final int  STATE_REFRESH = 1;
    private static final int STATE_MORE = 2;
    private int state = STATE_NORMAL;
    private static final int currentMinNum = 1;
    private int currentURLNum = 1 ;

    private String currentURL = "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-1.html";




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment,container,false);
        initView(view);
        initRefreshLayout();
        getCommentDatas();

        return  view;


    }
    //获取单页数据

    private void getCommentDatas() {
        if(commentBeanList != null){
            commentBeanList.clear();
        }

      commentBeanList = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {


                try{
                 document = Jsoup.connect(currentURL).timeout(10000).get();


                }catch(IOException e){
                    e.printStackTrace();
                }
                if(document != null){
                    TestGetComment testGetComment = new TestGetComment();
                    commentBeanList = testGetComment.getCommenBean(document);

                }else{
                    Toast.makeText(getContext(),"无更多数据",Toast.LENGTH_SHORT).show();
                }

                // handler.obtainMessage(1,commentBeanList);
               handler.sendEmptyMessage(1);
            }
        }).start();

    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {



            showDataAtTime(commentBeanList);



        }
    };
    private void showDataAtTime(final List<CommentBean> list){
        commentAdapter = new CommentAdapter(list);
        rvComment.setAdapter(commentAdapter);

        commentAdapter.setMyOnCommentListener(new CommentAdapter.MyOnCommentListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                String commentURL = list.get(position).getCommentUrl();
                intent.putExtra(MyStaticDatas.TheCommentURL,commentURL);
                startActivity(intent);

            }
        });


        rvComment.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvComment.setItemAnimator(new DefaultItemAnimator());

    }




    //弃用该方法，不适用
   /* private void showData(final List<CommentBean> list) {
       // commentAdapter = new CommentAdapter(commentBeanList);

        switch(state){

            case STATE_NORMAL:
                //该行？？？？
                commentAdapter = new CommentAdapter(list);
                rvComment.setAdapter(commentAdapter);

                commentAdapter.setMyOnCommentListener(new CommentAdapter.MyOnCommentListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(getActivity(), ArticleDetailActivity.class);
                            String commentURL = list.get(position).getCommentUrl();
                            intent.putExtra(MyStaticDatas.TheCommentURL,commentURL);
                            startActivity(intent);
                        }
                });

                rvComment.setLayoutManager(new LinearLayoutManager(getActivity()));
                rvComment.setItemAnimator(new DefaultItemAnimator());
                break;
            case STATE_REFRESH:



               commentAdapter.addData(commentAdapter.getDataSize(),list);
                rvComment.scrollToPosition(0);
                refreshComment.finishRefresh();
                state = STATE_NORMAL;
                break;
            case STATE_MORE:

               /commentAdapter.addData(commentAdapter.getDataSize(),list);
                rvComment.scrollToPosition(commentAdapter.getDataSize());
                refreshComment.finishRefreshLoadMore();
               //加上此行，点击正常，加载不正常，无法上滑
                state = STATE_NORMAL;
                break;
        }

    }*/

    private void initView(View view) {
        rvComment = (RecyclerView) view.findViewById(R.id.rv_comment);
        refreshComment = (MaterialRefreshLayout) view.findViewById(R.id.refresh_comment);

    }



    private void initRefreshLayout(){
        refreshComment.setLoadMore(true);
        refreshComment.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getLastPageDatas();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //下拉加载更多  一能只增加1
                getMoreDatas();


            }
        });
    }
    public void getMoreDatas(){


            currentURLNum  = currentURLNum + 1;
            refreshComment.finishRefreshLoadMore();
            Toast.makeText(getActivity(),"++当前页的值"+currentURLNum,Toast.LENGTH_SHORT).show();
            currentURL = "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-1.html";
            getCommentDatas();




    }
    public void getLastPageDatas(){
        if(currentURLNum > currentMinNum){

            currentURLNum = currentURLNum - 1;
            currentURL = "http://www.wcsfa.com/scfbox_list-"+currentURLNum+"-1.html";
            refreshComment.finishRefresh();
            getCommentDatas();

        }
    }







}
