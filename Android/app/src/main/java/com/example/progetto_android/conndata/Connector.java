package com.example.progetto_android.conndata;

import android.content.Context;
import android.util.Log;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.repositories.CorsiRepository;
import com.example.progetto_android.conndata.repositories.LoginRepository;
import com.example.progetto_android.conndata.repositories.ProfRepository;
import com.example.progetto_android.conndata.repositories.SlotRepository;
import com.example.progetto_android.conndata.utils.HttpRequest;

public class Connector {

    private HttpRequest http;
    private final LoginRepository loginRep;
    private final ProfRepository profRep;
    private final SlotRepository slotRep;
    private final CorsiRepository courseRep;
    private static Connector instance;
    //private String servlet;
    //private PrenotazioneRepository prenotazionerep;

    private Connector(Context appContext/*, String servlet*/) {
        String ip = appContext.getString(R.string.ip);
        String port = appContext.getString(R.string.port);
        String context = appContext.getString(R.string.context);
        String servlet = appContext.getString(R.string.servlet);

        http = new HttpRequest(ip, port, context, servlet);

        loginRep = new LoginRepository(http);
        courseRep = new CorsiRepository(http);
        profRep = new ProfRepository(http);
        slotRep = new SlotRepository(http);
    }

    public LoginRepository getLoginRep() {
        return loginRep;
    }

    public CorsiRepository getCorsoRep() {
        return courseRep;
    }

    public ProfRepository getProfRep() {
        return profRep;
    }

    public SlotRepository getSlotRep() {
        return slotRep;
    }

    public static synchronized Connector getInstance(Context appContext) {
        if (instance == null) {
            Log.i("47 Connector", "istanzio il connector generico");
            instance = new Connector(appContext);
        } else { //TODO eliminare una volta finito tutto
            Log.i("52 Connector", "il connector è già istanziato, quindi rimando quello");
        }
        return instance;
    }

}
