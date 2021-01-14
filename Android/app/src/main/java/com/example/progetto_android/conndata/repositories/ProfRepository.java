package com.example.progetto_android.conndata.repositories;

import android.util.Log;

import com.example.progetto_android.NewPrenotazione;
import com.example.progetto_android.conndata.utils.HttpRequest;
import com.example.progetto_android.conndata.utils.OnDaoCallCompleted;
import com.example.progetto_android.view.professori.Professore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfRepository {

    private HttpRequest http;
    private Gson gson;

    public ProfRepository(HttpRequest http){
        this.http = http;
        this.gson = new Gson();
    }

    public void findAll(OnDaoCallCompleted<List<Professore>> callback){
        //TODO eliminare una volta finito tutto
        Log.i("22 ProfRep", "Sono dento findAll");

        Map<String, String> params = new HashMap<>();

        params.put("action", "searchProf");

        params.put("corso", NewPrenotazione.getCourse().getNomeCorso());

        //TODO eliminare una volta finito tutto
        Log.i("40 ProfRep", "Corso inviato: " + NewPrenotazione.getCourse().getNomeCorso());

        http.sendRequest(params,result -> {
            try{
                ArrayList<Professore> profs = new ArrayList<>();

                //TODO eliminare una volta finito tutto
                Log.i("38 ProfRep", "result code: " + result.getResult().getStatusCode());
                Log.i("46 ProfRep", "result data: " + result.getResult().getData());

                if(result.getResult().getStatusCode() == HttpURLConnection.HTTP_OK){
                    Type listType = new TypeToken<ArrayList<Professore>>(){}.getType();

                    //TODO eliminare una volta finito tutto
                    Log.i("48 ProfRep", "sono dentro l'if del try");

                    profs = gson.fromJson(result.getResult().getData(), listType);

                    //TODO eliminare una volta finito tutto
                    Log.i("53 ProfRep", profs.toString());
                }else{
                    //TODO eliminare una volta finito tutto
                    Log.i("56 ProfRep", "Non sono entrato dentro l'if");
                }

                callback.onDaoCallCompleted(profs);
            }catch (IOException e){
                Log.e("ProfRep", e.getMessage(), e);

                if(callback != null)
                    callback.onDaoCallCompleted(null);
            }
        });
    }
}
