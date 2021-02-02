package com.example.progetto_android.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//intermezzo tra fragment e view/layout, permette di ottenere i dati contenuti nel layout
public class LoginViewModel extends ViewModel {

    //possono essere osservati tramite observer così da informare chi li sta osservando quando cambiano
    private MutableLiveData<String> username;
    private MutableLiveData<String> password;

    public MutableLiveData<String> getUsername() {
        if (username == null) {
            username = new MutableLiveData<>();
            username.setValue("");
        }
        return username;
    }

    public MutableLiveData<String> getPassword() {
        if (password == null) {
            password = new MutableLiveData<>();
            password.setValue("");
        }
        return password;
    }
}
