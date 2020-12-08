package com.example.progetto_android.view.professori;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Professore implements Serializable {

    private int idDocente;
    private String nome;
    private String cognome;
    private List<String> disponibilità;

    public Professore(int idDocente, String nome, String cognome){
        this.idDocente = idDocente;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Professore(){}

    public int getID(){
        return idDocente;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public void setIdDocente(int id){
        this.idDocente = id;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getProf(){
        return nome + "" + cognome;
    }

    @Override
    public String toString(){
        return "Docente{ID=" + idDocente + ", nome=" + nome + ", cognome=" + cognome + '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDocente);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Professore teacher = (Professore) o;
        return idDocente == teacher.idDocente &&
                Objects.equals(nome, teacher.nome) &&
                Objects.equals(cognome, teacher.cognome);
    }
}
