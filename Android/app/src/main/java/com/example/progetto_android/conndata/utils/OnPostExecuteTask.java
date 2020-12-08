package com.example.progetto_android.conndata.utils;

//serve per la funzione lambda
public interface OnPostExecuteTask {
    void execute(HttpTaskResult<HttpResponse> result);
}
