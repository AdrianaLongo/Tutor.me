package com.example.progetto_android.view;

import com.example.progetto_android.view.corsi.Corso;
import com.example.progetto_android.view.professori.Professore;

import java.util.List;

//classe usata per raccogliere le informazioni man a mano che vengono selezionate dall'utente
public class Prenotazione {

    private Corso corso;
    private List<Professore> professore;
    private int slot;

    public Prenotazione() {}

    public Corso getCorso(){
        return this.corso;
    }

    public List<Professore> getProfessore(){
        return this.professore;
    }

    public int getSlot(){
        return this.slot;
    }

    public void setCorso(Corso corso){
        this.corso = corso;
    }

    public void setProfessore(Professore professore){
        this.professore.add(professore);
    }

    public void setSlot(int slot){
        this.slot = slot;
    }
}
