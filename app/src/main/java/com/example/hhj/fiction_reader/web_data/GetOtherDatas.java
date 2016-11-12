package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by backfire on 2016/10/15.
 */
public class GetOtherDatas extends  GetBasicDatas {
    @Override
    public List<BasicBean> getBasicBeanList(Document document) {
        basicBeanList = new ArrayList<>();
        Elements basicElements = document.select("div.list_mix").first().select("ul").select("li");
        for(Element element:basicElements){
            String imgUrl = element.select("a").select("img").attr("abs:src");
            String textUrl = element.select("a").attr("abs:href");
            String title = element.select("a").select("img").attr("title");
            String brief = element.select("div.list_mix_show").select("p").text();
            basicBean = new BasicBean(imgUrl,textUrl,title,brief);
            basicBeanList.add(basicBean);
        }


        return basicBeanList;
    }


    @Override
    public List<Map<String, String>> getBasicDetail(Document document,String title) {
        return null;
    }

    @Override
    public List<BasicBean> getBasicBeanListByString(String string) {
        return null;
    }
}
