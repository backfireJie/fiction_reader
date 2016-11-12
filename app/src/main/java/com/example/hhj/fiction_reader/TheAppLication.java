package com.example.hhj.fiction_reader;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by XX on 2016/9/19.
 */
public class TheAppLication extends Application {
    private static RequestQueue queue;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getQueue(){
        //RequestQueue只需要获取一次实例
        return queue;
    }
}
