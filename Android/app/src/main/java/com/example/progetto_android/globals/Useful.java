package com.example.progetto_android.globals;

import androidx.annotation.NonNull;

//Classe di risposta http generica
public class Useful{
    String message;
    int success;
    Object object;

    public Useful(String message, int success, Object object){
        this.message = message;
        this.success = success;
        this.object = object;
    }

    public String getMessage(){
        return message;
    }

    public int getSuccess(){
        return success;
    }

    public String getObject() {
        return object.toString();
    }

    @NonNull
    @Override
    public String toString() {
        return "message " + message + "success " + success + "jSessionId " + object;
    }
}
