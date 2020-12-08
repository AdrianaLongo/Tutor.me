package com.example.progetto_android.view.corsi;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//Prende i dati dalla repository e crea la lista dei corsi da inserire nel fragment
public class CorsoViewModel extends ViewModel {
    private final MutableLiveData<List<Corso>> corsi;

    public CorsoViewModel(Context context){
        corsi = new MutableLiveData<>();
        corsi.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Corso>> getCorsi(){
        return corsi;
    }

    public void setCorsi(List<Corso> corsi){
        this.corsi.setValue(corsi);
    }
}
