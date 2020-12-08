package com.example.progetto_android.view.professori;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ProfFactory implements ViewModelProvider.Factory{

    private final Context context;

    public ProfFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfViewModel.class)) {
            return (T) new ProfViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
