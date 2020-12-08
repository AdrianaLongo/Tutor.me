package com.example.progetto_android.conndata.utils;

//contiene i dati ricevuti in risposta alla request dalla servlet
public class HttpResponse {
    private String data;
    private int statusCode;

    public HttpResponse(String data, int statusCode){
        this.data = data;
        this.statusCode = statusCode;
    }

    public String getData(){
        return data;
    }

    public int getStatusCode(){
        return statusCode;
    }
}
