package dao;

public class Prenotazione {
    private int idPrenotazione; //
    private int idDocente;
    private String nomeCorso;
    private int idUtente;
    private String slot;
    private String stato;


    public Prenotazione(int idPrenotazione, String nomeCorso, int idDocente, int idUtente, String slot
    , String stato){
        this.idPrenotazione = idPrenotazione;
        this.idDocente = idDocente;
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

    public String getSlot() {return slot;}

    public String getStato() {return stato;}
}
