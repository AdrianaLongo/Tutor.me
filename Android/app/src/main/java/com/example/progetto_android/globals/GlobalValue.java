package com.example.progetto_android.globals;

import android.app.Application;

import com.example.progetto_android.view.calendar.Slot;
import com.example.progetto_android.view.course.Course;
import com.example.progetto_android.view.tutors.Tutor;

//Tiene in memoria i dati necessari per inviare richieste alle servlet o creare prenotazioni
public class GlobalValue extends Application {
    private static Course course;
    private static Tutor prof;
    private static String day;
    private static String hour;
    private static Slot slot;
    private static String jSessionId;
    private static String idPrenotazione;

    public static Course getCourse() {
        return course;
    }

    public static void setCourse(Course course) {
        GlobalValue.course = course;
    }

    public static Tutor getProf() {
        return prof;
    }

    public static void setProf(Tutor prof) {
        GlobalValue.prof = prof;
    }

    public static String getDay() {
        return day;
    }

    public static void setDay(String day) {
        GlobalValue.day = day;
    }

    public static String getHour(){
        return hour;
    }

    public static void setHour(String hour){
        GlobalValue.hour = hour;
    }

    public static Slot getSlot(){
        return slot;
    }

    public static void setSlot(Slot slot){
        GlobalValue.slot = slot;
    }

    public static String getjSessionId(){
        return jSessionId;
    }

    public static void setjSessionId(String jSessionId){
        GlobalValue.jSessionId = jSessionId;
    }

    public static String getIdPrenotazione() {
        return idPrenotazione;
    }

    public static void setIdPrenotazione(String idPrenotazione) {
        GlobalValue.idPrenotazione = idPrenotazione;
    }
}
