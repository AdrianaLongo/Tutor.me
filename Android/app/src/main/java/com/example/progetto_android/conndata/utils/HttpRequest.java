package com.example.progetto_android.conndata.utils;

import android.util.Log;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.MalformedURLException;
import java.util.Map;

//mand la richiesta alla servlet
public class HttpRequest {

    private final String url;

    public HttpRequest(String ip, String port, String context, String servlet){
        CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        url = "http://" + ip + ":" + port + "/" + context + "/" + servlet;
    }

    public void sendRequest(Map<String, String> params, OnHttpTaskCompleted<HttpResponse> callback){
        try{
            new HttpTask(url, params,callback).run();
        }catch(MalformedURLException e){
            Log.e("HttpRequestManager", e.getMessage(), e);
        }
    }
}
