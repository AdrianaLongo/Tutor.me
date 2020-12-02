package dao;

public class Corso {
    private String nome;

    public Corso(String nome){
        this.nome = nome;
    }


    public String getNome() {
        return nome;
    }
    @Override
    public String toString(){
        return nome;
    }
}
