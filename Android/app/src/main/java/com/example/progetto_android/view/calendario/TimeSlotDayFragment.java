package com.example.progetto_android.view.calendario;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.NewPrenotazione;
import com.google.android.material.tabs.TabLayout;

import java.util.stream.Collectors;

import static com.example.progetto_android.view.corsi.CourseFragment.distinctByKey;

// Questa classe serve per creare e riempire le RecyclerView di ogni Tab
public class TimeSlotDayFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SlotViewModel slotViewModel;
    private SlotRecyclerAdapter mAdapter;
    private Connector connector;
    private View section_view;
    private int mIndex;
    private ColorStateList def = null;
    private TextView fs;
    private TextView ss;
    private TextView ts;
    private TextView qs;

    public static TimeSlotDayFragment newInstance(int index) {
        TimeSlotDayFragment fragment = new TimeSlotDayFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        //TODO eliminare una volta finito tutto
        Log.i("45 SlotFragment", "index salvato: " + index);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        connector = Connector.getInstance(getContext());
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        mIndex = index;
        TabLayout tab = requireActivity().findViewById(R.id.tabs);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        section_view = inflater.inflate(R.layout.fragment_section_date, container, false);
        SlotFactory slotFactory = new SlotFactory(getActivity());
        slotViewModel = new ViewModelProvider(this, slotFactory).get(SlotViewModel.class);
        return section_view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PopupFragment pf = new PopupFragment(mIndex,getActivity());

        fs = view.findViewById(R.id.First_Slot);
        ss = view.findViewById(R.id.Second_Slot);
        ts = view.findViewById(R.id.Third_Slot);
        qs = view.findViewById(R.id.Fourth_Slot);

        slotViewModel.getSlot().observe(getViewLifecycleOwner(), slots -> {
            //TODO eliminare una volta finito tutto
            Log.i("86 SlotFragment", "Recupero Slot");

            //creo lista ordinata di orari da inserire nell'ordine corretto nella view
            slots = slots.stream()
                    .filter(distinctByKey(Slot::getSezione))
                    .collect(Collectors.toList());

            if (slots.isEmpty()) {
                section_view.setVisibility(View.GONE);
                view.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            } else {
                section_view.setVisibility(View.VISIBLE);
                view.findViewById(R.id.empty_view).setVisibility(View.GONE);
            }

            //TODO eliminare una volta finito tutto
            Log.i("109 SlotFragment", slots.toString());

            // salvo il colore iniziale delle textview per controllare poi se l'ho già modificato o meno
            if(def == null)
                def = fs.getTextColors();

            Log.i("129 SlotFragment", String.valueOf(fs.getTextColors()));
            for (Slot slot : slots) {
                char i = slot.getSezione().charAt(3);
                //TODO non entra nell'if non li vede uguali, capire il perché
                switch (mIndex) {
                    case 0:
                        if (slot.getSezione().contains("LUN")) {
                            //TODO eliminare una volta finito tutto
                            Log.i("161 SlotFragment", "Sono dentro il primo giorno nel primo spazio orario con" + slot.getSezione());
                            if (i == '1' && fs.getTextColors().equals(def)) {
                                Log.i("138 SlotFragment", slot.getSezione() + "positivo");
                                fs.setTextColor(Color.GREEN);
                                break;
                            } else if(fs.getTextColors().equals(def)) {
                                fs.setTextColor(Color.RED);
                            }
                            if (i == '2' && ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.GREEN);
                                break;
                            } else if(ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.RED);
                            }
                            if (i == '3' && ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.GREEN);
                                break;
                            } else if(ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.RED);
                            }
                            if (i == '4' && qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.GREEN);
                                break;
                            } else if(qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.RED);
                            }
                        }
                        break;
                    case 1:
                        if (slot.getSezione().contains("MAR")) {
                            if (i == '1' && fs.getTextColors().equals(def)) {
                                Log.i("138 SlotFragment", slot.getSezione() + "positivo");
                                fs.setTextColor(Color.GREEN);
                                break;
                            } else if(fs.getTextColors().equals(def)) {
                                fs.setTextColor(Color.RED);
                            }
                            if (i == '2' && ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.GREEN);
                                break;
                            } else if(ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.RED);
                            }
                            if (i == '3' && ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.GREEN);
                                break;
                            } else if(ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.RED);
                            }
                            if (i == '4' && qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.GREEN);
                                break;
                            } else if(qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.RED);
                            }
                        }
                        break;
                    case 2:
                        if (slot.getSezione().contains("MER")) {
                            if (i == '1' && fs.getTextColors().equals(def)) {
                                Log.i("138 SlotFragment", slot.getSezione() + "positivo");
                                fs.setTextColor(Color.GREEN);
                                break;
                            } else if(fs.getTextColors().equals(def)) {
                                fs.setTextColor(Color.RED);
                            }
                            if (i == '2' && ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.GREEN);
                                break;
                            } else if(ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.RED);
                            }
                            if (i == '3' && ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.GREEN);
                                break;
                            } else if(ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.RED);
                            }
                            if (i == '4' && qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.GREEN);
                                break;
                            } else if(qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.RED);
                            }
                        }
                        break;
                    case 3:
                        if (slot.getSezione().contains("GIO")) {
                            if (i == '1' && fs.getTextColors().equals(def)) {
                                Log.i("138 SlotFragment", slot.getSezione() + "positivo");
                                fs.setTextColor(Color.GREEN);
                                break;
                            } else if(fs.getTextColors().equals(def)) {
                                fs.setTextColor(Color.RED);
                            }
                            if (i == '2' && ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.GREEN);
                                break;
                            } else if(ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.RED);
                            }
                            if (i == '3' && ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.GREEN);
                                break;
                            } else if(ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.RED);
                            }
                            if (i == '4' && qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.GREEN);
                                break;
                            } else if(qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.RED);
                            }
                        }
                        break;
                    case 4:
                        if (slot.getSezione().contains("VEN")) {
                            if (i == '1' && fs.getTextColors().equals(def)) {
                                Log.i("138 SlotFragment", slot.getSezione() + "positivo");
                                fs.setTextColor(Color.GREEN);
                                break;
                            } else if(fs.getTextColors().equals(def)) {
                                fs.setTextColor(Color.RED);
                            }
                            if (i == '2' && ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.GREEN);
                                break;
                            } else if(ss.getTextColors().equals(def)) {
                                ss.setTextColor(Color.RED);
                            }
                            if (i == '3' && ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.GREEN);
                                break;
                            } else if(ts.getTextColors().equals(def)) {
                                ts.setTextColor(Color.RED);
                            }
                            if (i == '4' && qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.GREEN);
                                break;
                            } else if(qs.getTextColors().equals(def)) {
                                qs.setTextColor(Color.RED);
                            }
                        }
                        break;
                }
            }
        });

        //TODO inserire il collegamento con la pagine del login una volta definita per il controllo dell'utente loggato o meno
        //Confermo scelta
        fs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPrenotazione.setHour((String) fs.getText());
                    pf.show();
            }
        });
        ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPrenotazione.setHour((String) ss.getText());
                pf.show();
            }
        });
        ts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPrenotazione.setHour((String) ts.getText());
                pf.show();
            }
        });
        qs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPrenotazione.setHour((String) qs.getText());
                pf.show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        //TODO eliminare una volta finito tutto
        Log.i("120 SlotFragment", "Sono dentro l'onResume");

        // Aggiorno la lista quando per esempio si ritorna a questa activity dopo avere disdetto una prenotazione
        connector.getSlotRep().findAll(result -> {

            //TODO eliminare una volta finito tutto
            Log.i("128 SlotFragment", "Sono dentro l'onResume");

            if (result != null) {

                //TODO eliminare una volta finito tutto
                Log.i("128 SlotFragment", "Ho ottenuto la lista degli Slot");

                slotViewModel.setSlots(result);
            } else {

                //TODO eliminare una volta finito tutto
                Log.i("133 SlotFragment", "Non ho ottenuto la lista degli Slot");

                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });
        /*connector.getLoginRep().loggedIn(result -> {
            if (result != null) {
                sd.setLogin(result);

                //TODO eliminare una volta finito tutto
                Log.i("185 CourseFragment", "Ho controllato il login");

            } else {
                sd.setLogin(new Login(null, false, null, null, false));
            }
        });*/
    }

}