package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.view.course.Course;
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
        this.gson = new Gson();
    }

    public void findCourses(OnDaoCallCompleted<List<Course>> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("method", "GET");
        //invio la servlet che mi serve col metodo GET
        params.put("url", "?" + "action=" + "populateCourses");

        http.sendRequest(params, result -> {
            try{
                ArrayList<Course> corsi = new ArrayList<>();

                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    //creo un nuovo tipo dove poter inserire i risultati ottenuti dalla call alla servlet
                    Type listType = new TypeToken<ArrayList<Course>>(){}.getType();

                    corsi = gson.fromJson( result.getResult().getData(),listType);
                }

                callback.onDaoCallCompleted(corsi);
            } catch (IOException e) {
                Log.e("CorsoRep", e.getMessage(), e);

                //Se c'è stato un errore setto la callback dal DAO su null per segnalarlo
                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }

}
