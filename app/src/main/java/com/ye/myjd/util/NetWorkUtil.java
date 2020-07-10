package com.ye.myjd.util;

import android.net.Uri;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.ye.myjd.bean.RResult;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : WoDong
 * @date : 2020/3/15 19:05
 * @desc : 网络请求的工具类
 */
public class NetWorkUtil {

    /**
     * post请求
     * @param url 请求的url
     * @param params 请求的参数
     * @return 返回json数据
     */
    public static String doPost(String url , HashMap<String,String> params) {
        try {
            URL uri = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            String requestUrl = "";
            Set<Map.Entry<String, String>> entries = params.entrySet();
            for (Map.Entry<String, String> entry: entries) {
                requestUrl += "&" + entry.getKey() + "=" + entry.getValue();
            }
            requestUrl = requestUrl.substring(1);
            Log.i("song", "doPost: "+requestUrl);
            conn.getOutputStream().write(requestUrl.getBytes());
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode:" +responseCode);
            if (responseCode == 200) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String json = br.readLine();
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String doGet(String urlPath) {
        try {
            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String json = br.readLine();
                return json;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
