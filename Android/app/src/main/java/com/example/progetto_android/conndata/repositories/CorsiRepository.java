package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.view.corsi.Corso;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

//invia la httprequest alla servlet per ricevere i corsi disponibili
public class CorsiRepository {

    private HttpRequest http;
    private Gson gson;

    public CorsiRepository(HttpRequest http){
        this.http = http;
        this.gson = gson;
    }

    public void findAll(OnDaoCallCompleted<List<Corso>> callback){

        //creo mappa richiesta
        Map<String, String> params = new HashMap<>();

        params.put("action", "get_ripetizioni_disponibili");

        http.sendRequest(params,result -> {
            try{
                List<Corso> corsi = null;

                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    //creo un nuovo tipo dove poter inserire i risultati ottenuti dalla call alla servlet
                    Type listType = new TypeToken<ArrayList<Corso>>(){}.getType();
                    corsi = gson.fromJson(result.getResult().getData(), listType);
                }

                callback.onDaoCallCompleted(corsi);
            } catch (IOException e) {
                Log.e("RipetizioneDAOError", e.getMessage(), e);

                //Se c'è stato un errore setto la callback dal DAO su null per segnalarlo
                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

}
