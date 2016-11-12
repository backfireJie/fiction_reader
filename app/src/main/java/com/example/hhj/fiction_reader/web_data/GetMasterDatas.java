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
 * Created by XX on 2016/10/13.
 */
public class GetMasterDatas extends  GetBasicDatas{
    private ArrayList<Map<String,String>> mapList;

    @Override
    public List<BasicBean> getBasicBeanList(Document document) {
        basicBeanList = new ArrayList<>();
        Elements masterElements = document.select("div.list_item").first().select("ul").select("li");

        for(Element element:masterElements){
            String imgUrl = element.select("a.thumb").select("img").attr("abs:src");
            String textUrl = element.select("a.thumb").attr("abs:href");
            String title = element.select("a.list_item_t").text();
            String brief = element.select("a.thumb").select("div.thumb_info").select("p").text();
            String time = element.select("div.list_item_tag").select("span").text();

            basicBean = new BasicBean(imgUrl,textUrl,title,brief,time);
            basicBeanList.add(basicBean);


        }


        return  basicBeanList;
    }


    public ArrayList<Map<String,String>> getBasicDetail(Document document,String title) {
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
            if(i == 0){
                singleLine.put("string","标题"+title);

            }
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
