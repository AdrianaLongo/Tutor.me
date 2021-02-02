package dao;

public class Utente {
    private int id;
    private String nome;
    private String cognome;
    private String ruolo;
    private String username;
    private String password;

    public Utente(int id, String nome, String cognome, String ruolo, String username, String password){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.ruolo = ruolo;
        this.username = username;
        this.password = password;
    }

    public int getId() {
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

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    @Override
    public String toString(){
        return "id " + id + " " + "username " + username + " " + "password " + password + " " + "nome " + nome + " " + "cognome " + cognome + " " + "ruolo " + ruolo;
    }
}