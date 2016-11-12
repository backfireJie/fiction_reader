package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XX on 2016/10/13.
 */
public class GetAnecdoteDatas {
    private BasicBean basicBean;
    private List<BasicBean> anecodoteBeanList;

        public List<BasicBean> getAnecodoteList(Document document){
            anecodoteBeanList = new ArrayList<>();

            Elements masterElements = document.select("div.list_mix").first().select("ul").select("li");
            if(masterElements != null){
                for(Element element:masterElements){
                    String imgUrl =null;
                    String textUrl = element.select("div.list_mix_show").select("h3").select("a").attr("abs:href");
                    String title =element.select("div.list_mix_show").select("h3").select("a").text();
                    String brief = element.select("div.list_mix_show").select("p").text();
                    basicBean = new BasicBean(imgUrl,textUrl,title,brief);
                    anecodoteBeanList.add(basicBean);

                }
            }else{

            }



            return   anecodoteBeanList;
        }
    }


