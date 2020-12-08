package com.example.progetto_android.view.cronologia;

import java.util.Objects;

public class Prenotazione {

    private int idPrenotazione;     //
    private String materia;         //
    private int idDocente;          //
    private int idUtente;           //
    private String docente;         //
    private String slot;            //

    public Prenotazione(int idPrenotazione, String materia, int idDocente, int idUtente, String slot, String docente){
        this.idPrenotazione = idPrenotazione;
        this.materia = materia;
        this.idDocente = idDocente;
        this.idUtente = idUtente;
        this.docente = docente;
        this.slot = slot;
    }

    public Prenotazione(){}

    public String getDocente(){
        return this.docente;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public String getMateria() {
        return materia;
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

    public void setIdPrenotazione(int idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }

    public void setMateria(String materia) {
        this.materia = materia;
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


    @Override
    public String toString() {
        return "Prenotazione{" +
                "idPrenotazione=" + idPrenotazione +
                ", nomeCorso='" + materia + '\'' +
                ", idDocente=" + idDocente +
                ", idUtente=" + idUtente +
                ", slot='" + slot + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prenotazione that = (Prenotazione) o;
        return idPrenotazione == that.idPrenotazione &&
                idDocente == that.idDocente &&
                idUtente == that.idUtente &&
                Objects.equals(materia, that.materia) &&
                Objects.equals(slot, that.slot);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.idPrenotazione);
        return hash;
    }
}
