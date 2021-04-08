package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.Useful;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

//classe che si occupa di recuperare i dati del login dalla servlet e li inserisce nella classe login
public class LoginRepository {

    private HttpRequest http;
    private Gson gson;

    public LoginRepository(HttpRequest http) {
        this.http = http;
        this.gson = new Gson();
    }

    public void login(String username, String password, OnDaoCallCompleted<Useful> callback) {
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();
        params.put("action", "login");
        params.put("username", username);
        params.put("password", password);
        params.put("method", "POST");

        //mando la richiesta
        //la lambda viene usata per creare un oggetto di classe login riempito con i dati
        //ricevuti dalla result,
        //il metodo sendRequest viene eseguito in modo asincrono, quindi prima viene eseguito quello
        //e poi la lambda che prenderà i valori ricevuti dalla richiesta e li inserirà nel metodo login
        http.sendRequest(params, result -> {
            try {
                Useful useful = null;
                //controllo se sono riuscito a connettermi
                if (result.getResult().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    //inserisco il risultato che ottengo in formato json
                    // all'interno della classe login
                    useful = gson.fromJson(result.getResult().getData(), Useful.class);
                }

                if (callback != null)
                    callback.onDaoCallCompleted(useful);
            } catch (IOException e) {
                Log.e("LoginRepError", e.getMessage(), e);
            }
        });
    }

    public void logout(OnDaoCallCompleted<Useful> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "logout");
        params.put("jSessionId", GlobalValue.getjSessionId());
        params.put("method", "POST");

        http.sendRequest(params, result -> {
            try {
                Useful useful = null;
                //controllo se sono riuscito a connettermi
                if (result.getResult().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    //inserisco il risultato che ottengo in formato json
                    // all'interno della classe login
                    useful = gson.fromJson(result.getResult().getData(), Useful.class);
                }

                if (callback != null)
                    callback.onDaoCallCompleted(useful);
            }catch (IOException e){
                Log.e("LogoutDAOError", e.getMessage(), e);

                //Richiamo la callback passando null per indicare la presenza di un errore
                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }
}


