package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.Useful;
import com.example.progetto_android.view.history.Book;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class BookRepository {
    private HttpRequest http;
    private Gson gson;

    public BookRepository(HttpRequest http){
        this.http = http;
        this.gson = new Gson();
    }

    public void sendBook(OnDaoCallCompleted<Useful> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("action", "doBooking");
        params.put("idDocente", GlobalValue.getProf().getId());
        params.put("nomeCorso", GlobalValue.getCourse().getNomeCorso());
        params.put("slot", GlobalValue.getSlot().getSezione());
        params.put("jSessionId", GlobalValue.getjSessionId());
        params.put("method", "POST");

        http.sendRequest(params,result -> {
            try {
                Useful res = null;
                //riverso il risultato della richiesta http
                if (result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK) {
                    res = gson.fromJson(result.getResult().getData(), Useful.class);
                }
                callback.onDaoCallCompleted(res);
            }catch(IOException e){
                Log.e("PrenotazioneRep - sendBook", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

    public void getBook(OnDaoCallCompleted<List<Book>> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("method", "GET");
        params.put("url", "?" + "action=" + "getUserHistory" + "&" + "jSessionId=" + GlobalValue.getjSessionId());

        http.sendRequest(params,result -> {
            try {
                List<Book> res = null;
                //riverso il risultato della richiesta http
                if (result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK) {
                    Type listType = new TypeToken<List<Book>>(){}.getType();
                    res = gson.fromJson(result.getResult().getData(), listType);
                }
                callback.onDaoCallCompleted(res);
            }catch(IOException e){
                Log.e("PrenotazioneRep - getBook", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

    public void deleteBook(OnDaoCallCompleted<Useful> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("action", "cancelReservation");
        params.put("idPrenotazione", GlobalValue.getIdPrenotazione());
        params.put("jSessionId", GlobalValue.getjSessionId());
        params.put("method", "POST");

        http.sendRequest(params,result -> {
            try{
                Useful res = null;
                //riverso il risultato della richiesta http
                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    res = gson.fromJson(result.getResult().getData(), Useful.class);
                }
                callback.onDaoCallCompleted(res);
            }catch (IOException e){
                Log.e("PrenotazioneRep - deleteBook", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

    public void doneBook(OnDaoCallCompleted<Useful> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("action", "lessonDone");
        params.put("idPrenotazione", GlobalValue.getIdPrenotazione());
        params.put("jSessionId", GlobalValue.getjSessionId());
        params.put("method", "POST");

        http.sendRequest(params,result ->{
            try{
                Useful res = null;
                //riverso il risultato della richiesta http
                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    res = gson.fromJson(result.getResult().getData(), Useful.class);
                }
                callback.onDaoCallCompleted(res);
            }catch (IOException e){
                Log.e("PrenotazioneRep - doneBook", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }
}
