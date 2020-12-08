package com.example.progetto_android.view.corsi;

import java.io.Serializable;
import java.util.Objects;

public class Corso implements Serializable{

    private String nomeCorso;

    public Corso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }

    public Corso(){}

    public String getCorso(){
        return nomeCorso;
    }

    public void setNomeCorso(String nomeCorso){
        this.nomeCorso = nomeCorso;
    }

    @Override
    public String toString() {
        return "Corso{" + "nomeCorso='" + nomeCorso + '\'' + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.nomeCorso);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corso corso = (Corso) o;
        return Objects.equals(nomeCorso, corso.nomeCorso);
    }

}
