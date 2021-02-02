package com.example.progetto_android.globals;

import androidx.lifecycle.MutableLiveData;

//questa classe serve per trasportare le informazioni relative alla situazione di login dell'utente
public class ShareDataViewModel {

    private MutableLiveData<Boolean> loggedIn;
    private static ShareDataViewModel instance;

    private ShareDataViewModel(){}

    public MutableLiveData<Boolean> getLoggedIn(){
        if(loggedIn == null)
            loggedIn = new MutableLiveData<>(false);
        return loggedIn;
    }

    public void setLoggedIn(Boolean data){
        loggedIn = getLoggedIn();
        loggedIn.postValue(data);          //tutti quelli che stanno osservando questo valore catturano il cambiamento
    }

    //grazie a questo metodo garantisco la presenza di una sola istanza,
    // in questo modo tutti i framgent si interfacceranno sempre con la stessa
    public static synchronized ShareDataViewModel getInstance(){
        if(instance == null){
            instance = new ShareDataViewModel();
        }
        return instance;
    }

}
