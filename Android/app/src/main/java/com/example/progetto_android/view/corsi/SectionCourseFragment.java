package com.example.progetto_android.view.corsi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Dao;
import com.example.progetto_android.globals.ShareDataViewModel;
import com.example.progetto_android.view.login.Login;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class SectionCourseFragment extends Fragment implements CorsiRecyclerAdapter.ItemClickListener{
    private ShareDataViewModel sd;
    private CorsoViewModel mViewModel;
    private CorsiRecyclerAdapter mAdapter;
    private Dao dao;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd = ShareDataViewModel.getInstance();
        //TODO finire
        //sd = new ViewModelProvider(requireActivity().get(ShareDataViewModel.class));
        dao = Dao.getInstance(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_section_course, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        recyclerView = view.findViewById(R.id.courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CorsoFactory factory = new CorsoFactory(getActivity());
        mViewModel = new ViewModelProvider(this, factory).get(CorsoViewModel.class);

        mAdapter = new CorsiRecyclerAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        //inserisco i corsi nella view prendendoli dalla repository che ne crea la lista
        mViewModel.getCorsi().observe(getViewLifecycleOwner(),corsi -> {
            corsi = corsi.stream()
                    //:: keyword => nomeClasse :: nomeMetodo, compattare l'invocazione di un metodo
                    .filter(distinctByKey(Corso::getCorso))
                    //ordino la lista dei corsi da mostrare
                    .sorted(Comparator.comparing(Corso::getCorso))
                    .collect(Collectors.toList());

            if(corsi.isEmpty()){
                recyclerView.setVisibility(View.GONE);
                view.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
                view.findViewById(R.id.empty_view).setVisibility(View.GONE);
            }
        });
        //serve per recuperare il navController e muoversi al fragment successivo
        view.findViewById(R.id.button_course).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SectionCourseFragment.this)
                        .navigate(R.id.menu_course_to_menu_prof);
            }
        });
    }

    //sovrascrivo il menu creato nell'activity nel caso in cui l'utente sia loggato o meno
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        requireActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

        sd.getLogin().observeForever(data -> {
            MenuItem item;
            if (data.getLoggedIn()) {
                item = menu.findItem(R.id.action_login);
                item.setVisible(false);
                item = menu.findItem(R.id.action_logout);
                item.setVisible(true);
                item = menu.findItem(R.id.action_prenotazioni);
                item.setVisible(true);
            } else {
                item = menu.findItem(R.id.action_login);
                item.setVisible(true);
                item = menu.findItem(R.id.action_logout);
                item.setVisible(false);
                item = menu.findItem(R.id.action_prenotazioni);
                item.setVisible(false);
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    //serve una volta che l'app viene riportata in primo piano, bisogna capire dove inserirla
    @Override
    public void onResume() {
        super.onResume();

        // Aggiorno la lista quando per esempio si ritorna a questa activity dopo avere disdetto una prenotazione
        dao.getCorsoRep().findAll(result -> {
            if (result != null) {
                mViewModel.setCorsi(result);
            } else {
                Toast.makeText(getContext(), "Controllare la connessione a internet", Toast.LENGTH_LONG).show();
            }
        });

        dao.getLoginRep().loggedIn(result -> {
            if (result != null) {
                sd.setLogin(result);
            } else {
                sd.setLogin(new Login(false, null, false));
            }
        });
    }

    //continuare, decidere scelta di cosa inviare o cosa fare una volta premuta la materia interessata
    @Override
    public void onItemClick(View view, int position) {
        String corso = ((CorsiRecyclerAdapter) recyclerView.getAdapter()).getItem(position).getCorso();
        Context context = view.getContext();
    }

    //controlla se nella mappa è presente la chiave che rappresente quell'oggetto
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}