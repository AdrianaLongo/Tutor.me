package com.example.progetto_android.view.course;

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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseFragment extends Fragment {
    private ShareDataViewModel sd;
    private CourseViewModel mViewModel;
    private CourseRecyclerAdapter mAdapter;
    private Connector connector;
    private RecyclerView recyclerView;
    private Activity a;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section_course, container, false);
        //viewModel che si occupa di gestire il collegamente tra layout e fragment
        mViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //istanzio la recyclerView
        recyclerView = view.findViewById(R.id.courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //ottengo i corsi dalla ViewModel e li inserisco nella view
        mViewModel.getCourse().observe(getViewLifecycleOwner(), corsi -> {
            /*il metodo stream serve per iniziare un insieme di operazioni che portano a settare
                una collezione di oggetti secondo un ordine, ci sono diversi metodi che si
                possono inserire nell'intermezzo
             */
            corsi = corsi.stream()
                    //:: keyword => nomeClasse :: nomeMetodo, compattare l'invocazione di un metodo
                    .filter(distinctByKey(Course::getNomeCorso))
                    //ordino la lista dei corsi da mostrare in base al nome
                    .sorted(Comparator.comparing(Course::getNomeCorso))
                    //metodo finale per ottenere il risultato delle operazioni precedenti
                    .collect(Collectors.toList());

            //controllo che ci siano dei corsi disponibili, altrimenti imposto un messaggio nella view
            if (corsi.isEmpty()) {
                recyclerView.setVisibility(View.GONE);
                view.findViewById(R.id.empty_view_course).setVisibility(View.VISIBLE);
            } else {
                recyclerView.setVisibility(View.VISIBLE);
                view.findViewById(R.id.empty_view_course).setVisibility(View.GONE);
            }

            //attraverso l'adapter posso impostare i row della recyclerView riempiendoli con i corsi ottenuti
            mAdapter = new CourseRecyclerAdapter(getActivity(), corsi);
            recyclerView.setAdapter(mAdapter);
        });

        //serve per recuperare il navController e muoversi tra i fragment
        view.findViewById(R.id.button_course).setOnClickListener(v -> {
            if (GlobalValue.getCourse() != null) {
                NavHostFragment.findNavController(CourseFragment.this)
                        .navigate(R.id.menu_course_to_menu_prof);
            } else {
                Toast.makeText(getContext(), "Perfavore scegliere un corso prima di proseguire", Toast.LENGTH_LONG).show();
            }
        });
    }

    //sovrascrivo menu se utente loggato o meno
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        //controllo che sia loggato
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
        // Aggiorno la lista quando per esempio si ritorna a questa activity dopo avere disdetto una prenotazione
        connector.getCourseRep().findCourses(result -> {
            if (result != null) {
                //inserisco i risultati nel viewModel per poterli recuperare nel momento in cui viene creata la view
                mViewModel.setCourse(result);
            } else {
                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });
        //gestisco il pulsante back
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                sd.getLoggedIn().observe(CourseFragment.this, data -> {
                    if (data) {
                        connector.getLoginRep().logout(res -> {
                            sd.setLoggedIn(false);
                            Toast.makeText(getContext(), res.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    }
                    NavHostFragment.findNavController(CourseFragment.this)
                            .navigate(R.id.menu_course_to_login);
                });
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    //per sicurezza prendo l'activity e la inserisco in una variabile
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.a = (Activity) context;
        }

    }

    //controlla se nel layout è già presente la chiave che rappresente quell'oggetto
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}