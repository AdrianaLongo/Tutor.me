package com.example.progetto_android.conndata.utils;

//serve per passare il risultato ottenuto dalla call alle servlet
public interface OnPostExecuteTask {
    void execute(HttpTaskResult<HttpResponse> result);
}
