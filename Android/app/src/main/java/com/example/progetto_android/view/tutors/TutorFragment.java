package com.example.progetto_android.view.tutors;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.ShareDataViewModel;

import java.util.Comparator;
import java.util.stream.Collectors;

import static com.example.progetto_android.view.course.CourseFragment.distinctByKey;

public class TutorFragment extends Fragment {

    private TutorViewModel mViewModel;
    private TutorRecyclerAdapter mAdapter;
    private Connector connector;
    private RecyclerView recyclerView;
    private ShareDataViewModel sd;
    private Activity a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        //necessario per poter modificare il menu
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section_prof, container, false);

        //viewModel che si occupa di gestire il collegamente tra layout e fragment
        mViewModel = new ViewModelProvider(this).get(TutorViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //istanzio la recyclerView
        recyclerView = view.findViewById(R.id.profs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //ottengo i corsi dalla ViewModel e li inserisco nella view
        mViewModel.getProfs().observe(getViewLifecycleOwner(), profs -> {
            profs = profs.stream()
                    .filter(distinctByKey(Tutor::getId))
                    //organizzo la raccolta in base al cognome del professore
                    .sorted(Comparator.comparing(Tutor::getCognome))
                    .collect(Collectors.toList());

            //controllo che ci siano dei corsi disponibili, altrimenti imposto un messaggio nella view
            if (profs.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                view.findViewById(R.id.empty_view_prof).setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                view.findViewById(R.id.empty_view_prof).setVisibility(View.GONE);
            }

            //attraverso l'adapter posso impostare i row della recyclerView riempiendoli con i corsi ottenuti
            mAdapter = new TutorRecyclerAdapter(getActivity(), profs);
            recyclerView.setAdapter(mAdapter);
        });

        //una volta premuto il bottone recupero il navController per andare al prossimo fragment
        view.findViewById(R.id.button_prof).setOnClickListener(v -> {
            if (GlobalValue.getProf() != null) {
                NavHostFragment.findNavController(TutorFragment.this)
                        .navigate(R.id.menu_prof_to_menu_calendar);
            } else {
                Toast.makeText(getContext(), "Perfavore scegliere un professore prima di proseguire", Toast.LENGTH_LONG).show();
            }
        });
    }

    //sovrascrivo menu se utente loggato o meno
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        sd.getLoggedIn().observeForever(data -> {

            if (data) {
                menu.clear();
                a.getMenuInflater().inflate(R.menu.menu_logged_in, menu);
            } else {
                menu.clear();
                a.getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    //check item dal menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            connector.getLoginRep().logout(res -> {
                sd.setLoggedIn(false);
                Toast.makeText(getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
            });
        }
        return super.onOptionsItemSelected(item);
    }

    //Questo metodo viene chiamato subito, permette di recuperare i corsi non appena viene chiamato questo fragment
    @Override
    public void onResume() {        //utile quando, ad esempio il telefono viene ruotato e l'activity distrutta e ricreata
        super.onResume();
        connector.getProfRep().findAll(result -> {

            if (result != null) {
                //inserisco i risultati nel viewModel per poterli recuperare nel momento in cui viene creata la view
                mViewModel.setProf(result);
            } else {
                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.a = (Activity) context;
        }

    }
}