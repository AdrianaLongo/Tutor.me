package com.example.progetto_android.view.course;

import java.util.Objects;

public class Course {

    private final String nome;

    public Course(String nome){
        this.nome = nome;
    }

    public String getNomeCorso(){
        return nome;
    }

    @Override
    public String toString() {
        return "Corso{" + "Materia= " + nome + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course corso = (Course) o;
        return Objects.equals(nome, corso.nome);
    }

}
