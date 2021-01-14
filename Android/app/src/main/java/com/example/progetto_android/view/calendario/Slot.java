package com.example.progetto_android.view.calendario;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Slot implements Serializable {
    private String sezione = "";
    public Slot(String slot) {
        this.sezione = slot;
    }

    public String getSezione() {
        return this.sezione;
    }

    public String getHour(){
        return getHour(sezione);
    }

    private String getHour(String text){
        switch(text) {
            case "LUN1":
            case "MAR1":
            case "MER1":
            case "GIO1":
            case "VEN1":
                return "15-16";
            case "LUN2":
            case "MAR2":
            case "MER2":
            case "GIO2":
            case "VEN2":
                return "16-17";
            case "LUN3":
            case "MAR3":
            case "MER3":
            case "GIO3":
            case "VEN3":
                return "17-18";
            case "LUN4":
            case "MAR4":
            case "MER4":
            case "GIO4":
            case "VEN4":
                return "18-19";
            default:
                return null;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return this.sezione + " " + getHour(sezione);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.sezione);
        return hash;
    }
}