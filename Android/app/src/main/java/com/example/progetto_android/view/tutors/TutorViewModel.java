package com.example.progetto_android.view.tutors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//intermezzo tra fragment e view/layout, permette di ottenere i dati contenuti nel layout
public class TutorViewModel extends ViewModel {
    //possono essere osservati tramite observer così da informare chi li sta osservando quando cambiano
    private final MutableLiveData<List<Tutor>> profs;

    public TutorViewModel(){
        profs = new MutableLiveData<>();
        profs.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Tutor>> getProfs(){return profs;}

    public void setProf(List<Tutor> profs){
        this.profs.setValue(profs);
    }
}