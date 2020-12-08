package com.example.progetto_android.view.professori;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProfViewModel extends ViewModel {
    private final MutableLiveData<List<Professore>> prof;

    public ProfViewModel(Context context){
        prof = new MutableLiveData<>();
        prof.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Professore>> getProf(){return prof;}
}