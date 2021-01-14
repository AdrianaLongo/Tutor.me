package com.example.progetto_android.view.calendario;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progetto_android.NewPrenotazione;
import com.example.progetto_android.R;

//TODO cercare come ingrandire il popup e migliorare disposizione caselle
public class PopupFragment extends Dialog implements View.OnClickListener {

    private final int index;
    private Button confirm, reject;
    private TextView course;
    private TextView prof;
    private TextView day;
    private TextView hour;

    public PopupFragment(int index, Activity a) {
        super(a);
        this.index = index;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_popup);
        course = findViewById(R.id.lvl1c);
        prof = findViewById(R.id.lvl2c);
        day = findViewById(R.id.lvl3c);
        hour = findViewById(R.id.lvl4c);
        confirm = findViewById(R.id.confirm);
        reject = findViewById(R.id.reject);
    }

    private void riempi() {
        switch (index) {
            case 0:
                NewPrenotazione.setDay("Lunedì");
                break;
            case 1:
                NewPrenotazione.setDay("Martedì");
                break;
            case 2:
                NewPrenotazione.setDay("Mercoledì");
                break;
            case 3:
                NewPrenotazione.setDay("Giovedì");
                break;
            case 4:
                NewPrenotazione.setDay("Venerdì");
                break;
        }

        course.setText((CharSequence) NewPrenotazione.getCourse().getNomeCorso());
        prof.setText((CharSequence) NewPrenotazione.getProf().getProf());
        day.setText((CharSequence) NewPrenotazione.getDay());
        hour.setText((CharSequence) NewPrenotazione.getHour());

        //TODO eliminare una volta finito tutto
        Log.i("67 PopupFragment", NewPrenotazione.getHour());
    }
    //TODO inserire il collegamento con la pagina della cronologia dove si vedrà la conferma del corso
    @Override
    protected void onStart() {
        super.onStart();
        riempi();
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hai premuto confirm", Toast.LENGTH_LONG).show();
            }
        });
        reject.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Hai premuto reject", Toast.LENGTH_LONG).show();
                dismiss();
            }
        });
    }

    //necessario per implementare i listener
    @Override
    public void onClick(View v) {

    }
}