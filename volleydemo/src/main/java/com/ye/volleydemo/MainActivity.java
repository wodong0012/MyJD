package com.ye.volleydemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view) {
        String url = "https://www.baidu.com/";
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            //当数据返回了 就调用该方法 该方法在主线程中运行
            @Override
            public void onResponse(String response) {
                Log.i("song", "onResponse: "+response);
            }
        }, null);
        //请求队列  封装线程池   缓存  ....  系统建议 全局只创建一个线程池就可以了
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
