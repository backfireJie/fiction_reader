package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.MyStaticDatas;
import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/11/7.
 */
public class GetFurtureDatas {
    private BasicBean basicBean;
    private List<BasicBean> basicBeanList;




    public List<BasicBean> getAllDatas(String string) {
        Document document = Jsoup.parse(string);
        //System.out.println("document"+document);
        Elements futureElements = document.select("div.list").select("div.left").select("ul").select("li");

        for(Element element : futureElements){
            String imgUrl = element.select("a").select("img").attr("src");
            String textUrl = element.select("a").attr("href");
            String title = element.select("a").select("p.tit").text();
            String name = element.select("a").select("p.name").text();
            String brief =element.select("p").select("font").text();
            basicBean = new BasicBean(MyStaticDatas.ABSImg+imgUrl,MyStaticDatas.ABSText+textUrl,title,brief);
            basicBeanList = new ArrayList<>();
            basicBeanList.add(basicBean);
        }
        return  basicBeanList;
    }

    public List<BasicBean> getHotDatas(String string){

        Document document = Jsoup.parse(string);
        Elements hotElements = document.select("div.list").select("div.right").select("ul").select("li");

        for(Element element : hotElements){
            String title = element.select("a").attr("href");
            String textUrl = element.select("a").text();
            basicBean = new BasicBean(title,MyStaticDatas.ABSText+textUrl,null);
            basicBeanList = new ArrayList<>();
            basicBeanList.add(basicBean);

        }
        return  basicBeanList;
    }


}
