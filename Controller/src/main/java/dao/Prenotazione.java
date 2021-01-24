package dao;

public class Prenotazione {
    private int idPrenotazione;
    private int idDocente;
    private String nomeDocente;
    private String cognomeDocente;
    private String nomeCorso;
    private int idUtente;
    private String slot;
    private String stato;


    public Prenotazione(int idPrenotazione, String nomeCorso, int idDocente, String nomeDocente, String cognomeDocente, int idUtente, String slot
    , String stato){
        this.idPrenotazione = idPrenotazione;
        this.idDocente = idDocente;
        this.nomeDocente = nomeDocente;
        this.cognomeDocente = cognomeDocente;
        this.nomeCorso = nomeCorso;
        this.idUtente = idUtente;
        this.slot = slot;
        this.stato = stato;
    }

    public int getIdPrenotazione() {
        return idPrenotazione;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }

    public int getIdUtente() {return idUtente;}

    public String getNomeDocente(){return nomeDocente;}

    public String getCognomeDocente(){return cognomeDocente;}

    public String getSlot() {return slot;}

    public String getStato() {return stato;}
}
