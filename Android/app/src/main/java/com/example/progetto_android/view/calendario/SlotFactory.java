package com.example.progetto_android.view.calendario;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SlotFactory implements ViewModelProvider.Factory{
    private final Context context;

    public SlotFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SlotViewModel.class)) {
            return (T) new SlotViewModel(context);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
