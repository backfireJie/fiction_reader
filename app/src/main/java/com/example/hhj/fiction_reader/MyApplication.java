package com.example.hhj.fiction_reader;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by backfire on 2016/10/21.
 */
public class MyApplication extends Application {

    private static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue(){
        //RequestQueue只需要获取一次实例
        return queue;
    }
}
