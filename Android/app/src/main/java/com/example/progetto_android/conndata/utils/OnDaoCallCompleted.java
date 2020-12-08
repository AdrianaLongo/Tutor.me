package com.example.progetto_android.conndata.utils;

//la usa come classe di appoggio per tenere in memoria dei risultati
public interface OnDaoCallCompleted<T> {
    void onDaoCallCompleted(T result);
}
