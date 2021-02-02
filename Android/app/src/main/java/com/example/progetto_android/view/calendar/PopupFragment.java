package com.example.progetto_android.view.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.ShareDataViewModel;

//classe che fa comparire un dialog riassuntivo sulle scelte per la prenotazione dell'utente
public class PopupFragment extends DialogFragment implements View.OnClickListener {

    private final int index;
    private Button confirm, reject;
    private TextView course;
    private TextView prof;
    private TextView day;
    private TextView hour;
    private final ShareDataViewModel sd;
    private final Activity a;
    Connector connector;

    public PopupFragment(int index, Activity a) {
        super();
        this.a = a;
        this.index = index;
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popup, container, true);
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());

        course = view.findViewById(R.id.lvl1c);
        prof = view.findViewById(R.id.lvl2c);
        day = view.findViewById(R.id.lvl3c);
        hour = view.findViewById(R.id.lvl4c);
        confirm = view.findViewById(R.id.confirm);
        reject = view.findViewById(R.id.reject);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //metodo per riempire le TextView con le scelte prese dall'utente
        riempi();
        //listeners sull'accettazione o l'annullamento da parte dell'utente
        confirm.setOnClickListener(v -> {
            //controllo se l'utente è loggato
            check();
            dismiss();
        });
        reject.setOnClickListener(v -> {
            Toast.makeText(a, "Nessuna prenotazione effettuata", Toast.LENGTH_LONG).show();
            dismiss();
        });
    }

    private void riempi() {
        switch (index) {
            case 0:
                GlobalValue.setDay("Lunedì");
                break;
            case 1:
                GlobalValue.setDay("Martedì");
                break;
            case 2:
                GlobalValue.setDay("Mercoledì");
                break;
            case 3:
                GlobalValue.setDay("Giovedì");
                break;
            case 4:
                GlobalValue.setDay("Venerdì");
                break;
        }

        course.setText(GlobalValue.getCourse().getNomeCorso());
        prof.setText(GlobalValue.getProf().getProf());
        day.setText(GlobalValue.getDay());
        hour.setText(GlobalValue.getHour());
    }

    public void check() {
        sd.getLoggedIn().observe(this, data -> {
            if (data) {
                connector.getBookRep().sendBook(result -> {
                    Toast.makeText(a, result.getMessage(), Toast.LENGTH_LONG).show();
                });
            } else {
                Toast.makeText(a, "Prima di poter effettuare la prenotazione devi identificarti", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
    }
}
