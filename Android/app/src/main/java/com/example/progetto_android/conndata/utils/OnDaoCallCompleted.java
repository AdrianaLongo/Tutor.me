package com.example.progetto_android.conndata.utils;

//la uso come classe di appoggio per tenere in memoria i risultati e passarli dalle repository ai viewModel
public interface OnDaoCallCompleted<T> {
    void onDaoCallCompleted(T result);
}
