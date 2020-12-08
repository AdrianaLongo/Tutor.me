package com.example.progetto_android.conndata.dto;

import java.util.Objects;

public class Utente {

    private int idUtente;
    private String username;
    private String password;
    private boolean isAdmin;

    public Utente(int idUtente, String username, String password, boolean admin){
        this.idUtente = idUtente;
        this.username = username;
        this.password = password;
        this.isAdmin = admin;
    }

    public Utente(){}

    public int getIdUtente(){
        return idUtente;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdmin(){
        return isAdmin;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return idUtente == utente.idUtente &&
                isAdmin == utente.isAdmin &&
                Objects.equals(username, utente.username) &&
                Objects.equals(password, utente.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtente);
    }
}
