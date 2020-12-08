package com.example.progetto_android.view.cronologia;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CronologiaViewModel extends ViewModel {
    private final MutableLiveData<List<Prenotazione>> prenotazione;

    public CronologiaViewModel(Context context){
        prenotazione = new MutableLiveData<>();
        prenotazione.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Prenotazione>> getPrenotazione(){return prenotazione;}
}
