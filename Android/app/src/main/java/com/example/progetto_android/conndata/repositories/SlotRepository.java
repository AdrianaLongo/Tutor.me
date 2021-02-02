package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.view.calendar.Slot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class SlotRepository {

    private HttpRequest http;
    private Gson gson;

    public SlotRepository(HttpRequest http){
        this.http  = http;
        this.gson = new Gson();
    }

    public void findAll(OnDaoCallCompleted<List<Slot>> callback){
        //inserisco i parametri necessari per identificare la servlet che mi serve e per effettuare la query richiesta
        Map<String, String> params = new HashMap<>();

        params.put("method", "GET");
        params.put("url", "?" + "action=" + "getTutorAvailability" + "&" + "idDocente=" + GlobalValue.getProf().getId());

        http.sendRequest(params,result -> {
            try{
                ArrayList<Slot> slots = new ArrayList<>();
                //riverso il risultato della richiesta http
                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    Type listType = new TypeToken<ArrayList<Slot>> (){}.getType();
                    slots = gson.fromJson(result.getResult().getData(), listType);
                }
                callback.onDaoCallCompleted(slots);
            } catch (IOException e) {
                Log.e("SlotRep", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });

    }
}
