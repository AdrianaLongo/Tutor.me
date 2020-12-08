package com.example.progetto_android.view.corsi;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CorsoFactory implements ViewModelProvider.Factory {

    private final Context context;

    public CorsoFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CorsoViewModel.class)) {
            return (T) new CorsoViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}