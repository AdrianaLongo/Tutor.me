package com.example.progetto_android.view.tutors;

import java.util.Objects;

public class Tutor {

    private String nome;
    private String cognome;
    private String id;

    public Tutor(String id, String nome, String cognome){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
    }

    public Tutor(){}

    public String getId(){
        return id;
    }

    public String getNome(){
        return nome;
    }

    public String getCognome(){
        return cognome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getProf(){
        return nome + " " + cognome;
    }

    @Override
    public String toString(){
        return "Docente{ID=" + id + ", nome=" + nome + ", cognome=" + cognome + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tutor teacher = (Tutor) o;
        return id.equals(teacher.id) &&
                Objects.equals(nome, teacher.nome) &&
                Objects.equals(cognome, teacher.cognome);
    }
}
