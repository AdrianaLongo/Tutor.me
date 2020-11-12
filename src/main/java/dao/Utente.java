package dao;

public class Utente {
    private String id;
    private String nome;
    private String cognome;
    private String ruolo;

    public Utente(String id, String nome, String cognome, String ruolo){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    @Override
    public String toString(){
        return id + " " + nome + " " + cognome + " " + ruolo;
    }
}
