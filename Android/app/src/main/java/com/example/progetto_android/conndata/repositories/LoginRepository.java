package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.view.login.Login;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

//classe che si occupa di recuperare i dati del login dalla servlet e li inserisce nella classe login
public class LoginRepository {

    private HttpRequest http;
    private Gson gson;

    public LoginRepository(HttpRequest http) {
        this.http = http;
        this.gson = new Gson();
    }

    public void login(String username, String password, OnDaoCallCompleted<Login> callback) {
        //creo hashmap contenente i parametri da passare alla richiesta http
        Map<String, String> params = new HashMap<>();
        params.put("action", "login");
        params.put("username", username);
        params.put("password", password);

        //mando la richiesta
        //la lambda viene usata per creare un oggetto di classe login riempito con i dati
        //ricevuti dalla result,
        //il metodo sendRequest viene eseguito in modo asincrono, quindi prima viene eseguito quello
        //e poi la lambda che prenderà i valori ricevuti dalla richiesta e li inserirà nella classe login
        http.sendRequest(params, result -> {
            try {
                Login utente = null;
                //controllo se sono riuscito a connettermi
                if (result.getResult().getStatusCode() == HttpURLConnection.HTTP_OK) {
                    //inserisco il risultato che ottengo in formato json
                    // all'interno della classe login
                    utente = gson.fromJson(result.getResult().getData(), Login.class);
                }

                if (callback != null)
                    callback.onDaoCallCompleted(utente);
            } catch (IOException e) {
                Log.e("LoginDAOError", e.getMessage(), e);
            }
        });
    }

    public void logout(OnDaoCallCompleted<Boolean> callback) {
        Map<String, String> params = new HashMap<>();
        params.put("action", "logout");

        http.sendRequest(params, result -> {
            try {
                if (callback != null) {
                    callback.onDaoCallCompleted(result.getResult().getStatusCode() == HttpURLConnection.HTTP_OK);
                }
            }catch (IOException e){
                Log.e("LogoutDAOError", e.getMessage(), e);

                //Richiamo la callback passando null per indicare la presenza di un errore
                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

    public void loggedIn(OnDaoCallCompleted<Login> callback){
        Map<String, String> params = new HashMap<>();
        params.put("action", "logged_in");

        http.sendRequest(params, result -> {
            try{
                Login loggedIn = null;

                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    loggedIn = gson.fromJson(result.getResult().getData(), Login.class);
                }

                if(callback != null)
                    callback.onDaoCallCompleted(loggedIn);
            }catch(IOException e){
                Log.e("LoggedInDAOError", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }
}


