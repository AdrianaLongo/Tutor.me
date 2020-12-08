package com.example.progetto_android.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.progetto_android.view.cronologia.Prenotazione;

import java.util.ArrayList;
import java.util.List;

public class PrenotazioniUtenteViewModel extends ViewModel {

    private final MutableLiveData<List<Prenotazione>> prenotazioniUtente;

    public PrenotazioniUtenteViewModel(){
        prenotazioniUtente = new MutableLiveData<>();
        prenotazioniUtente.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Prenotazione>> getPrenotazioniUtente(){
        return prenotazioniUtente;
    }

    public void setPrenotazioniUtente(List<Prenotazione> prenotazioniUtente){
        this.prenotazioniUtente.setValue(prenotazioniUtente);
    }
}
