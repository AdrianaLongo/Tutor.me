package com.example.progetto_android.view.history;

import java.util.Objects;

public class Book {

    private int idPrenotazione;
    private int idDocente;
    private String nomeDocente;
    private String cognomeDocente;
    private String nomeCorso;
    private int idUtente;
    private String slot;
    private String stato;

    public Book(int idPrenotazione, String cognomeDocente, String nomeCorso, int idDocente, int idUtente, String slot, String nomeDocente, String stato){
        this.idPrenotazione = idPrenotazione;
        this.cognomeDocente = cognomeDocente;
        this.nomeCorso = nomeCorso;
        this.idDocente = idDocente;
        this.idUtente = idUtente;
        this.nomeDocente = nomeDocente;
        this.slot = slot;
        this.stato = stato;
    }

    public Book(){}

    public String getNomeDocente(){
        return this.nomeDocente;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public String getCognomeDocente() {
        return cognomeDocente;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public String getSlot() {
        return slot;
    }

    public String getStato() {
        return stato;
    }

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public void setNomeCorso(String nomeCorso) {
        this.nomeCorso = nomeCorso;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public void setNomeDocente(String nomeDocente){
        this.nomeDocente = nomeDocente;
    }

    public void setCognomeDocente(String cognomeDocente) {
        this.cognomeDocente = cognomeDocente;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "idPrenotazione=" + idPrenotazione +
                ", nomeCorso='" + nomeCorso + '\'' +
                ", Docente=" + nomeDocente +
                " " + cognomeDocente +
                ", idDocente=" + idDocente +
                ", idUtente=" + idUtente +
                ", slot='" + slot + '\'' +
                ", stato= " + stato +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return idPrenotazione == that.idPrenotazione &&
                idDocente == that.idDocente &&
                idUtente == that.idUtente &&
                Objects.equals(nomeDocente, that.nomeDocente)&&
                Objects.equals(cognomeDocente, that.cognomeDocente)&&
                Objects.equals(nomeCorso, that.nomeCorso) &&
                Objects.equals(slot, that.slot);
    }
}
