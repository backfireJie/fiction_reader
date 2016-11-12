package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by backfire on 2016/10/15.
 */
public abstract class GetBasicDatas {
    protected BasicBean basicBean;

    protected List<BasicBean> basicBeanList;
    protected List<Map<String,String>> basicDetailList;
    public  abstract List<BasicBean> getBasicBeanList(Document document);
    public  abstract List<Map<String,String>> getBasicDetail(Document document,String title);
    public abstract  List<BasicBean> getBasicBeanListByString(String string);

}
