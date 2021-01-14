package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.NewPrenotazione;
import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.view.calendario.Slot;
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
        //TODO eliminare una volta finito tutto
        Log.i("28 SlotRep", "Sono dentro findAll");

        Map<String, String> params = new HashMap<>();
        params.put("action", "profAvailable");
        params.put("idDocente", NewPrenotazione.getProf().getID());

        http.sendRequest(params,result -> {
            try{
                ArrayList<Slot> slots = new ArrayList<>();

                //TODO eliminare una volta finito tutto
                Log.i("39 SlotRep", "result code: " + result.getResult().getStatusCode());
                Log.i("40 SlotRep", "result data: " + result.getResult().getData());

                if(result.getResult().getStatusCode() == HttpsURLConnection.HTTP_OK){
                    Type listType = new TypeToken<ArrayList<Slot>> (){}.getType();

                    //TODO eliminare una volta finito tutto
                    Log.i("50 SlotRep", "sono dentro l'if del try");

                    slots = gson.fromJson(result.getResult().getData(), listType);

                    //TODO eliminare una volta finito tutto
                    Log.i("56 SlotRep", slots.toString());
                }else
                    Log.i("59 SlotRep", "Non sono entrato dentro l'if");
                callback.onDaoCallCompleted(slots);
            } catch (IOException e) {
                Log.e("SlotRep", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });

    }
}
