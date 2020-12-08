package com.example.progetto_android.view.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

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

    public void setUsername(String username) {
        this.username = getUsername();
        this.username.setValue(username);
    }

    public void setPassword(String password) {
        this.password = getPassword();
        this.password.setValue(password);
    }
}
