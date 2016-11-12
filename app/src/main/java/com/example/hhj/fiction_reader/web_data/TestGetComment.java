package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;
import com.example.hhj.fiction_reader.bean.CommentBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/10.
 */
public class TestGetComment {
    private CommentBean commentBean;
    private BasicBean basicBean;
    private List<CommentBean> commentBeanList;



    public TestGetComment(){


    }

    public List<CommentBean>  getCommenBean(Document document){
        List<CommentBean> list = new ArrayList<>();
        Elements commentElements = document.select("div.list_mix").first().select("li");
        for(Element element:commentElements){
            String imgUrl = element.select("a").select("img").attr("abs:src");
            String commentUrl = element.select("a").attr("abs:href");
            String title = element.select("a").select("img").attr("title");
            String brief = element.select("div.list_mix_show").select("p").text();
            String time =  element.select("div.list_mix_show").select("span").text();
            commentBean = new CommentBean(imgUrl,commentUrl,title,brief,time);
            list.add(commentBean);



        }

        return  list;
 }
    //获取分章详情
    //TextView换行源字符串中要换行的地方加上 \n

    public List<String> getCommentDetail(Document document){
        List<String> articleDetail = new ArrayList<>();
        //第一行添加标题
        articleDetail.add("标题");
        //String[] articleDetail = new String[MyStaticDatas.arrayLong];
        Elements articleElements = document.select("div.article").first().select("div.text");

        for(int i =0;i<articleElements.size();i++) {


            String line = articleElements.get(i).select("p").select("span").text();
            String strongLine = articleElements.get(i).select("p").select("span").select("strong").text();
            String lineFeed = articleElements.get(i).select("p").select("span").attr("br");

            if (line != null) {
                if (strongLine != null && strongLine != "") {
                    line = "<strong>" + strongLine;
                }

                articleDetail.add(line);
            } else {
                break;
            }


        }


        return articleDetail;

    }

}
