package com.example.progetto_android.view.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.ShareDataViewModel;
import com.example.progetto_android.view.history.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.progetto_android.view.course.CourseFragment.distinctByKey;

// Questa classe serve per creare e riempire le tab
public class SlotDayFragment extends Fragment{

    private static final String ARG_SECTION_NUMBER = "section_number";

    private SlotViewModel mViewModel;
    private Connector connector;
    private int mIndex;
    private ShareDataViewModel sd;
    private final ArrayList<Book> books = new ArrayList<>();

    //chiamata per inizializzare il fragment con il numero della tab a indicare in quale giorno si trova
    public static SlotDayFragment newInstance(int index) {
        SlotDayFragment fragment = new SlotDayFragment();
        Bundle bundle = new Bundle();

        //tramite il bundle viene passato al fragment
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        mIndex = index;
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_section_date, container, false);
        //viewModel che si occupa di gestire il collegamente tra layout e fragment
        mViewModel = new ViewModelProvider(this).get(SlotViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //creo array dove andrò a suddividere gli orari ricevuti a seconda della necessità
        ArrayList<Slot> hours = new ArrayList<>();
        ArrayList<Book> uSlots = new ArrayList<>();

        TextView fs = view.findViewById(R.id.First_Slot);
        TextView ss = view.findViewById(R.id.Second_Slot);
        TextView ts = view.findViewById(R.id.Third_Slot);
        TextView qs = view.findViewById(R.id.Fourth_Slot);

        //ottengo gli slot dalla ViewModel e li inserisco nella view
        mViewModel.getSlots().observe(getViewLifecycleOwner(), slots -> {
            if (!slots.isEmpty()) {
                slots = slots.stream()
                        //ordino per giorno la lista degli orari ricevuti
                        .filter(distinctByKey(Slot::getSezione))
                        .collect(Collectors.toList());
                List<Slot> pSlots = slots;
                sd.getLoggedIn().observe(getViewLifecycleOwner(), data -> {
                    if (data) {
                        //ottengo le prenotazioni dell'utente
                        mViewModel.getBook().observe(getViewLifecycleOwner(), book -> {
                            book = book.stream()
                                    .filter(distinctByKey(Book::getSlot))
                                    .collect(Collectors.toList());
                            //seleziono quelle ancora attive
                            for (Book p : book) {
                                if (p.getStato().equals("0")) {
                                    uSlots.add(p);
                                }
                            }
                            //prendo gli orari del giorno che mi serve
                            for (Slot slot : pSlots) {
                                String day = slot.getDay(slot.getSezione());
                                switch (mIndex) {
                                    case 0:
                                        if (day.equals("Lunedì")) {
                                            hours.add(slot);
                                        }
                                        break;
                                    case 1:
                                        if (day.equals("Martedì")) {
                                            hours.add(slot);
                                        }
                                        break;
                                    case 2:
                                        if (day.equals("Mercoledì")) {
                                            hours.add(slot);
                                        }
                                        break;
                                    case 3:
                                        if (day.equals("Giovedì")) {
                                            hours.add(slot);
                                        }
                                        break;
                                    case 4:
                                        if (day.equals("Venerdì")) {
                                            hours.add(slot);
                                        }
                                        break;
                                }
                            }
                            //scelgo quali orari mostrare, e inserisco gli avvisi nel caso l'utente abbia già lo slot occupato
                            if (!hours.isEmpty()) {
                                for (Slot slot : hours) {
                                    switch (slot.getHour()) {
                                        case "15-16":
                                            if (check(uSlots, pSlots, slot.getSezione())) {
                                                fs.setVisibility(View.VISIBLE);
                                                view.findViewById(R.id.imageView1).setVisibility(View.VISIBLE);
                                            } else
                                                fs.setVisibility(View.VISIBLE);
                                            break;
                                        case "16-17":
                                            if (check(uSlots, pSlots, slot.getSezione())) {
                                                ss.setVisibility(View.VISIBLE);
                                                view.findViewById(R.id.imageView2).setVisibility(View.VISIBLE);
                                            } else
                                                ss.setVisibility(View.VISIBLE);
                                            break;
                                        case "17-18":
                                            if (check(uSlots, pSlots, slot.getSezione())) {
                                                ts.setVisibility(View.VISIBLE);
                                                view.findViewById(R.id.imageView3).setVisibility(View.VISIBLE);
                                            } else
                                                ts.setVisibility(View.VISIBLE);
                                            break;
                                        case "18-19":
                                            if (check(uSlots, pSlots, slot.getSezione())) {
                                                qs.setVisibility(View.VISIBLE);
                                                view.findViewById(R.id.imageView4).setVisibility(View.VISIBLE);
                                            } else
                                                qs.setVisibility(View.VISIBLE);
                                            break;
                                    }
                                }
                            } else
                                Toast.makeText(getContext(),
                                        "Questo professore è al completo per oggi, prova a cambiare giorno.",
                                        Toast.LENGTH_LONG).show();
                        });
                    }
                });
                //controllo sul click dell'utente per ogni textview
                fs.setOnClickListener(v -> onClick(view.findViewById(R.id.imageView1), hours, "15-16"));
                ss.setOnClickListener(v -> onClick(view.findViewById(R.id.imageView2), hours, "16-17"));
                ts.setOnClickListener(v -> onClick(view.findViewById(R.id.imageView3), hours, "17-18"));
                qs.setOnClickListener(v -> onClick(view.findViewById(R.id.imageView4), hours, "18-19"));
            }
        });
    }

    //Questo metodo viene chiamato subito, permette di recuperare i corsi non appena viene chiamato questo fragment
    @Override
    public void onResume() {
        super.onResume();
        // Aggiorno la lista quando per esempio si ritorna a questa activity
        connector.getSlotRep().findAll(result -> {
            if (result != null) {
                mViewModel.setSlots(result);
            } else {
                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });
        sd.getLoggedIn().observe(this, data -> {
            if (data) {
                connector.getBookRep().getBook(result -> {
                    if (result != null) {
                        mViewModel.setBook(result);
                    } else {
                        Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void onClick(ImageView tv, ArrayList<Slot> hours, String hour){
                if (tv.getVisibility() == View.INVISIBLE) {
                    selectSlot(hours, hour);
                    GlobalValue.setHour(hour);
                    new PopupFragment(mIndex, getActivity()).show(getChildFragmentManager(), null);
                } else {
                    Book book = new Book();
                    for(Slot s : hours){
                      if(s.getHour().equals(hour)){
                          for(Book p : books){
                              if(p.getSlot().equals(s.getSezione()))
                                  book = p;
                          }
                      }
                    }
                    AlertDialog dialog = new AlertDialog.Builder(requireContext()).setMessage("Lezione di " + book.getNomeCorso()).show();
                }
    }


    private boolean check(ArrayList<Book> uSlots, List<Slot> pSlots, String sezione) {
        for (Slot pSlot : pSlots) {
            for (Book uSlot : uSlots) {
                if (pSlot.getSezione().equals(uSlot.getSlot()) && pSlot.getSezione().equals(sezione)) {
                    books.add(uSlot);
                    return true;
                }
            }
        }
        return false;
    }

    //recupero lo slot selezionato dall'utente e lo inserisco nella prenotazione da inviare
    public static void selectSlot(List<Slot> slots, String hour) {
        for (Slot slot : slots) {
            if (slot.getHour().equals(hour)) {
                GlobalValue.setSlot(slot);
            }
        }
    }

}