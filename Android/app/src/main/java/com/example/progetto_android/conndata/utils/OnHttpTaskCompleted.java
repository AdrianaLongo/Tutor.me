package com.example.progetto_android.conndata.utils;

//interfaccia che recupera il risultato della task
public interface OnHttpTaskCompleted<T> {
    void onHttpTaskCompleted(HttpTaskResult<T> result);
}
