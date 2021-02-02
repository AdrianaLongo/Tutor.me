package com.example.progetto_android.view.history;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//intermezzo tra fragment e view/layout, permette di ottenere i dati contenuti nel layout
public class HistoryViewModel extends ViewModel {
    //possono essere osservati tramite observer così da informare chi li sta osservando quando cambiano
    private final MutableLiveData<List<Book>> prenotazioni;

    public HistoryViewModel(){
        prenotazioni = new MutableLiveData<>();
        prenotazioni.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Book>> getPrenotazioni(){return prenotazioni;}

    public void setPrenotazioni(List<Book> book) {
        this.prenotazioni.setValue(book);
    }
}
