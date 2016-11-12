package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XX on 2016/10/10.
 */
public class GetComment  extends  GetBasicDatas{

    private BasicBean basicBean;
    private List<Map<String,String>> mapList;

    public GetComment(){}

    @Override
    public List<BasicBean> getBasicBeanList(Document document) {
       List<BasicBean> list = new ArrayList<>();
        Elements commentElements = document.select("div.list_mix").first().select("li");
        for(Element element:commentElements){
            String imgUrl = element.select("a").select("img").attr("abs:src");
            String commentUrl = element.select("a").attr("abs:href");
            String title = element.select("a").select("img").attr("title");
            String brief = element.select("div.list_mix_show").select("p").text();
            String time =  element.select("div.list_mix_show").select("span").text();
            basicBean = new BasicBean(imgUrl,commentUrl,title,brief,time);
            list.add(basicBean);



        }

        return  list;
    }
    /*
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

    */


    @Override
    public List<Map<String, String>> getBasicDetail(Document document, String title) {
        mapList = new ArrayList<>();

        Elements articleElements = document.select("div.article").first().select("div.text").select("p");
       /* for(Element element:articleElements){
            String line  = element.select("span").text();
            singleLine.put("string",line);
            mapList.add(singleLine);
            System.out.println(mapList.toString());

        }*/

        for(int i = 0;i<articleElements.size();i++){
            Map<String, String> singleLine = new HashMap<>();

            String line  =articleElements.get(i).select("span").text();
            singleLine.put("string",line);
            mapList.add(singleLine);
        }
        return mapList;
    }

    @Override
    public List<BasicBean> getBasicBeanListByString(String string) {
        return null;
    }


}
