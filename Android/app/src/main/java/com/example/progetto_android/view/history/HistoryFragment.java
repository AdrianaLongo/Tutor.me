package com.example.progetto_android.view.history;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.progetto_android.view.course.CourseFragment.distinctByKey;

//Questa classe serve per gestire le prenotazioni effettuate dell'utente
public class HistoryFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section";
    private HistoryViewModel mViewModel;
    private HistoryRecyclerAdapter mAdapter;
    private Connector connector;
    private int mIndex;

    //chiamata per inizializzare il fragment con il numero della tab a indicare lo stato della prenotazione
    public static HistoryFragment newInstance(int index) {
        HistoryFragment fragment = new HistoryFragment();

        //tramite il bundle viene passato al fragment
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        mIndex = index;
        //gestisco il pulsante back
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                NavHostFragment.findNavController(HistoryFragment.this)
                        .navigate(R.id.menu_history_to_course);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        //viewModel che si occupa di gestire il collegamente tra layout e fragment
        mViewModel = new ViewModelProvider(this).get(HistoryViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.prenotazioni);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //ottengo le prenotazioni dalla ViewModel e li inserisco nella view
        mViewModel.getPrenotazioni().observe(getViewLifecycleOwner(), prenotazioni -> {
            if (!prenotazioni.isEmpty()) {
                prenotazioni = prenotazioni.stream()
                        .filter(distinctByKey(Book::getIdPrenotazione))
                        //ordino per giorni le prenotazioni dell'utente
                        .sorted(Comparator.comparing(Book::getSlot))
                        .collect(Collectors.toList());

                //scelgo solo le prenotazioni in relazione
                List<Book> specific = new ArrayList<>();
                switch (mIndex) {
                    case 0:
                        for (Book p : prenotazioni) {
                            if (p.getStato().equals("0")) {
                                specific.add(p);
                            }
                        }
                        if (specific.isEmpty()) {
                            Toast.makeText(getContext(), "Nessuna prenotazione effettuata", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1:
                        for (Book p : prenotazioni) {
                            if (p.getStato().equals("-1")) {
                                specific.add(p);
                            }
                        }
                        if (specific.isEmpty()) {
                            Toast.makeText(getContext(), "Nessuna prenotazione eliminata", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2:
                        for (Book p : prenotazioni) {
                            if (p.getStato().equals("1")) {
                                specific.add(p);
                            }
                        }
                        if (specific.isEmpty()) {
                            Toast.makeText(getContext(), "Nessuna lezione sostenuta", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                if (!specific.isEmpty()) {
                    //attraverso l'adapter posso impostare i row della recyclerView riempiendoli con i corsi ottenuti
                    mAdapter = new HistoryRecyclerAdapter(getActivity(), specific, mIndex);
                    recyclerView.setAdapter(mAdapter);
                }
            }
        });
    }

    //Questo metodo viene chiamato subito, permette di recuperare i corsi non appena viene chiamato questo fragment
    @Override
    public void onResume() {
        super.onResume();

        // Aggiorno la lista quando per esempio si ritorna a questa activity dopo avere disdetto una prenotazione
        connector.getBookRep().getBook(result -> {
            if (result != null) {
                mViewModel.setPrenotazioni(result);
            } else {
                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    //per sicurezza prendo l'activity e la inserisco in una variabile
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            Activity a = (Activity) context;
        }

    }
}