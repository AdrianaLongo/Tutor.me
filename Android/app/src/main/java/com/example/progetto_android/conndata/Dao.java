package com.example.progetto_android.conndata;

import android.content.Context;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.repositories.CorsiRepository;
import com.example.progetto_android.conndata.repositories.LoginRepository;
import com.example.progetto_android.conndata.utils.HttpRequest;

public class Dao {

    private HttpRequest http;
    private LoginRepository loginrep;
    //private PrenotazioneRepository prenotazionerep;
    private CorsiRepository corsorep;

    private static Dao instance;

    private Dao(Context appContext) {
        String ip = appContext.getString(R.string.ip);
        String port = appContext.getString(R.string.port);
        String context = appContext.getString(R.string.context);
        //TODO modificare ricezione nome servlet
        String servlet = appContext.getString(R.string.servlet);

        http = new HttpRequest(ip, port, context, servlet);

        loginrep = new LoginRepository(http);
        //prenotazionerep = new PrenotazioneRepository(http);
        corsorep = new CorsiRepository(http);
    }

    public LoginRepository getLoginRep(){
        return loginrep;
    }

    /*public PrenotazioneRepository getPrenotazioneRep(){
        return prenotazionerep;
    }*/

    public CorsiRepository getCorsoRep() {
        return corsorep;
    }
    public static synchronized Dao getInstance(Context appContext) {
        if (instance == null) {
            instance = new Dao(appContext);
        }
        return instance;
    }
}
