package com.example.hhj.fiction_reader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hhj.fiction_reader.http.OkHttpManager;
import com.example.hhj.fiction_reader.web_data.GetBasicDatas;
import com.example.hhj.fiction_reader.web_data.GetMasterDatas;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by backfire on 2016/10/16.
 */


public class ArticleDetailListViewActivity extends AppCompatActivity {
    private  ArrayList<Map<String, String>> textList = new ArrayList<>();


    private ListView lvListViews;
    private String commentURL;
    private String title;
    private List<String> detailList;
    private Document document;
    private GetBasicDatas getBasicDatas;

    private ArrayList<Map<String,String>> mapList;
    private String fromWhere;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.include_textview);

        lvListViews = (ListView) findViewById(R.id.lv_textviews);
        commentURL = getIntent().getStringExtra(MyStaticDatas.TheCommentURL);
        fromWhere = getIntent().getStringExtra(MyStaticDatas.ComeFromWhere);
        System.out.println(commentURL+","+fromWhere);
        if(fromWhere.equals(MyStaticDatas.FromMain)){
            getArticle();
        }else if(fromWhere.equals(MyStaticDatas.FromFuture)){
            getFutureArticle();
        }







    }

    private void getFutureArticle(){
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.getAsyn(commentURL, new OkHttpManager.DataRequestResponse() {
            @Override
            public void onDataResult(String htmlData) {

            }

            @Override
            public void onDataFailure(IOException e) {

            }
        });


    }



    private void getArticle(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    document = Jsoup.connect(commentURL).timeout(5000).get();
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                    getBasicDatas = new GetMasterDatas();
                    textList =(ArrayList<Map<String,String>>)  getBasicDatas.getBasicDetail(document,title);
                    handler.sendEmptyMessage(1);

            }

        }).start();



    }
    private void showDatas(ArrayList<Map<String,String>> list){

        SimpleAdapter simpleAdapter = new SimpleAdapter(ArticleDetailListViewActivity.this,list
                ,R.layout.template_textview_article,new String[]{"string"},new int[]{R.id.article_item});
        lvListViews.setAdapter(simpleAdapter);

    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            showDatas(textList);
        }
    };


}
