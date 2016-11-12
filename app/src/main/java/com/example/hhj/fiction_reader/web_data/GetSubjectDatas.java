package com.example.hhj.fiction_reader.web_data;

import com.example.hhj.fiction_reader.bean.BasicBean;

import org.jsoup.nodes.Document;

import java.util.List;
import java.util.Map;

/**
 * Created by backfire on 2016/10/15.
 */
public class GetSubjectDatas extends  GetBasicDatas {


    @Override
    public List<BasicBean> getBasicBeanList(Document document) {
        return null;
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
