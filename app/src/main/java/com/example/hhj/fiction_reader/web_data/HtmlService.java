package com.example.hhj.fiction_reader.web_data;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 废弃
 * Created by backfire on 2016/10/21.
 */
public class HtmlService {

    public static String getHtml(String path) throws  Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        InputStream inputStream = conn.getInputStream();
        byte []  data = readInputStream(inputStream);
        String html = new String(data,"utf-8");

        return  html;
    }

    private static byte[] readInputStream(InputStream inputStream) throws  Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len=inputStream.read(buffer)) != -1){
            outStream.write(buffer,0,len);
        }
        inputStream.close();
        return  outStream.toByteArray();

    }


}
