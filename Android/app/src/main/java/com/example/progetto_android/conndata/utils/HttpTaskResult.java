package com.example.progetto_android.conndata.utils;

import java.io.IOException;

/*
    contiene i risultati ottenuti dalla chiamata alle servlet
    ha due costruttori per separare i risultati dalle eccezioni lanicate da errori nella connessione

*/
public class HttpTaskResult<T> {
    private T result;
    private IOException ex;

    public HttpTaskResult(T result){
        super();
        this.result = result;
    }

    public HttpTaskResult(IOException ex){
        super();
        this.ex = ex;
    }

    public T getResult() throws IOException{
        if(ex != null)
            throw ex;
        return result;
    }
}
