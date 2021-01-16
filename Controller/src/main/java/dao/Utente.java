package dao;

public class Utente {
    private String id;
    private String nome;
    private String cognome;
    private String ruolo;
    private String password;
    private String username;

    public Utente(String id, String nome, String cognome, String ruolo, String password, String username){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.password = password;
        this.username = username;
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

    public String getPassword() {return password;}

    public String getUsername() { return username; }

    @Override
    public String toString(){
        return id + " " + username + " " +  password + " " + nome + " " + cognome + " " + ruolo;
    }
}
