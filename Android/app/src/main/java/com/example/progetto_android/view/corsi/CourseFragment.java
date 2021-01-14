package com.example.progetto_android.view.corsi;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.ShareDataViewModel;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CourseFragment extends Fragment implements CorsiRecyclerAdapter.ItemClickListener{
    private ShareDataViewModel sd;
    private CorsoViewModel mViewModel;
    private CorsiRecyclerAdapter mAdapter;
    private Connector connector;
    private RecyclerView recyclerView;

    //TODO cercare di salvare lo stato del fragment una volta già istanziato così da salvare la scelta quando torna indietro per cambiarla
    /*@Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        //Save the fragment's state here
        super.onSaveInstanceState(outState);
        outState.putInt("CheckPos", mAdapter.getSelectedPosition());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            //Restore the fragment's state here
            mAdapter.setSelectedPosition(savedInstanceState.getInt("CheckPos"));
        }
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd = ShareDataViewModel.getInstance();

        connector = Connector.getInstance(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section_course, container, false);

        CorsoFactory factory = new CorsoFactory(getActivity());

        mViewModel = new ViewModelProvider(this, factory).get(CorsoViewModel.class);

        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        recyclerView = view.findViewById(R.id.courses);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //inserisco i corsi nella view prendendoli dalla repository che ne crea la lista
        mViewModel.getCorsi().observe(getViewLifecycleOwner(),corsi -> {
            Log.i("CourseFragment", "Recupero Corsi");
            corsi = corsi.stream()
                    //:: keyword => nomeClasse :: nomeMetodo, compattare l'invocazione di un metodo
                    .filter(distinctByKey(Corso::getNomeCorso))
                    //ordino la lista dei corsi da mostrare
                    .sorted(Comparator.comparing(Corso::getNomeCorso))
                    .collect(Collectors.toList());

            if(corsi.isEmpty()){
                recyclerView.setVisibility(View.GONE);
                view.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
            }else{
                recyclerView.setVisibility(View.VISIBLE);
                view.findViewById(R.id.empty_view).setVisibility(View.GONE);
            }

            Log.i("89 CoursesFrag", corsi.toString());

            //tramite l'adapter inserisco nel ViewHolder i dati presi dal db
            mAdapter = new CorsiRecyclerAdapter(getActivity(), corsi);
            recyclerView.setAdapter(mAdapter);
        });

        //serve per recuperare il navController e muoversi al fragment successivo
        view.findViewById(R.id.button_course).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CourseFragment.this)
                        .navigate(R.id.menu_course_to_menu_prof);
            }
        });
    }

    //sovrascrivo il menu creato nell'activity nel caso in cui l'utente sia loggato o meno
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        requireActivity().getMenuInflater().inflate(R.menu.menu_main, menu);

        //TODO eliminare una volta finito tutto
        Log.i("139 CourseFragment", "" + sd.getLogin().getValue().getLoggedIn());

        sd.getLogin().observeForever(data -> {
            data = sd.getLogin().getValue();

            //TODO eliminare una volta finito tutto
            Log.i("145 CourseFragment", "" + data.getLoggedIn());

            MenuItem item;
            if (data.getLoggedIn()) {

                //TODO eliminare una volta finito tutto
                Log.i("144 Course_Menu", "sono dentro");

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

    //Questo metodo viene chiamato subito, permette di recuperare i corsi non appena viene chiamato questo fragment
    @Override
    public void onResume() {
        super.onResume();

        //TODO eliminare una volta finito tutto
        Log.i("178 CourseFragment", "Sono dentro l'onResume");

        // Aggiorno la lista quando per esempio si ritorna a questa activity dopo avere disdetto una prenotazione
        connector.getCorsoRep().findAll(result -> {
            if (result != null) {
                mViewModel.setCorsi(result);

                //TODO eliminare una volta finito tutto
                Log.i("174 CourseFragment", "Ho ottenuto la lista dei corsi");

            } else {
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

    //continuare, decidere scelta di cosa inviare o cosa fare una volta premuta la materia interessata
    @Override
    public void onItemClick(View view, int position) {
        String corso = ((CorsiRecyclerAdapter) recyclerView.getAdapter()).getItem(position).getNomeCorso();
        Context context = view.getContext();
    }

    //controlla se nella mappa è presente la chiave che rappresente quell'oggetto
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}