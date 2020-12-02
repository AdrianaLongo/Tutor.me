package dao;

public class Prenotazione {
    private String idPrenotazione;
    private String idDocente;
    private String nomeCorso;

    public Prenotazione(String idPrenotazione, String idDocente, String nomeCorso){
        this.idPrenotazione = idPrenotazione;
        this.idDocente = idDocente;
        this.nomeCorso = nomeCorso;
    }

    public String getIdPrenotazione() {
        return idPrenotazione;
    }

    public String getIdDocente() {
        return idDocente;
    }

    public String getNomeCorso() {
        return nomeCorso;
    }
}
