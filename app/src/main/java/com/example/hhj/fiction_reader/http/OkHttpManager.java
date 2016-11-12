package com.example.hhj.fiction_reader.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by XX on 2016/11/4.
 */
public class OkHttpManager {

    public interface DataRequestResponse{
        void onDataResult(String htmlData);
        void onDataFailure(IOException e);
    }
    public void getAsyn(final String url,final DataRequestResponse dataRequestResponse){

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dataRequestResponse.onDataFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dataRequestResponse.onDataResult(response.body().string());
            }
        });
    }





}
