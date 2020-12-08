package com.example.progetto_android.globals;

import androidx.lifecycle.MutableLiveData;

import com.example.progetto_android.view.login.Login;

//questa classe serve per trasportare le informazioni relative alla situazione di login dell'utente
public class ShareDataViewModel {

    private MutableLiveData<Login> login;

    private static ShareDataViewModel instance;

    private ShareDataViewModel(){}

    public MutableLiveData<Login> getLogin(){
        if(login == null){
            login = new MutableLiveData<>();
            login.setValue(new Login(false,null, false));
        }
        return login;
    }

    public void setLogin(Login data){
        login = getLogin();
        login.postValue(data);          //tutti quelli che stanno osservando questo valore vengono avvertiti di un cambiamento
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
