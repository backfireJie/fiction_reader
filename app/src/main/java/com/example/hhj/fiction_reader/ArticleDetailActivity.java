package com.example.hhj.fiction_reader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.hhj.fiction_reader.web_data.GetBasicDatas;
import com.example.hhj.fiction_reader.web_data.GetComment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 废弃
 * Created by XX on 2016/10/12.
 */
public class ArticleDetailActivity extends AppCompatActivity {

   private ArrayList<Map<String, String>> textList = new ArrayList<>();

    private ListView lvListViews;
    private String commentURL;
    private String title;
    private List<String> detailList;
    private Document document;
    //private GetBasicDatas getMasterDatas;
    private GetBasicDatas getBasicDatas;


   /*
   intent无法传递对象，也无法执行有参构造方法
   public ArticleDetailListViewActivity(GetBasicDatas getBasicDatas){
       this.getBasicDatas = getBasicDatas;
   }
*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.include_textview);
        lvListViews = (ListView) findViewById(R.id.lv_textviews);
        commentURL = getIntent().getStringExtra(MyStaticDatas.TheCommentURL);
       // title = getIntent().getStringExtra(MyStaticDatas.TheTitle);


    }


    private void getFutureArticle() {
       /* OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.getAsyn(commentURL, new OkHttpManager.DataRequestResponse() {
            @Override
            public void onDataResult(String htmlData) {
                Document document = Jsoup.parse(htmlData);
                System.out.println(document.toString());
                mapList = new ArrayList<>();
              Elements articleElements = document.select("div.txt_reader").select("div.db").select("div.text_container").select("p");
                System.out.println(articleElements.toString());



            }

            @Override
            public void onDataFailure(IOException e) {

            }
        });*/
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(commentURL)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().toString());
                Log.i("text",response.body().toString());
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
                getBasicDatas = new GetComment();
                textList =(ArrayList<Map<String,String>>)  getBasicDatas.getBasicDetail(document,title);
                handler.sendEmptyMessage(1);

            }

        }).start();
    }
    private void showDatas(ArrayList<Map<String,String>> list){

        SimpleAdapter simpleAdapter = new SimpleAdapter(ArticleDetailActivity.this,list
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
