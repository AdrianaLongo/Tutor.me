package com.example.progetto_android.view.cronologia;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class CronologiaFactory implements ViewModelProvider.Factory {

    private final Context context;

    public CronologiaFactory(android.content.Context context){this.context = context;}

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom((CronologiaViewModel.class))){
            return (T) new CronologiaViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
