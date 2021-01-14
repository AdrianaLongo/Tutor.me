package com.example.progetto_android;

import android.app.Application;

import com.example.progetto_android.view.corsi.Corso;
import com.example.progetto_android.view.professori.Professore;

public class NewPrenotazione extends Application {
    private static Corso course;
    private static Professore prof;
    private static String day;
    private static String hour;


    public static Corso getCourse() {
        return course;
    }

    public static void setCourse(Corso course) {
        NewPrenotazione.course = course;
    }

    public static Professore getProf() {
        return prof;
    }

    public static void setProf(Professore prof) {
        NewPrenotazione.prof = prof;
    }

    public static String getDay() {
        return day;
    }

    public static void setDay(String day) {
        NewPrenotazione.day = day;
    }

    public static String getHour(){
        return hour;
    }

    public static void setHour(String hour){
        NewPrenotazione.hour = hour;
    }
}
