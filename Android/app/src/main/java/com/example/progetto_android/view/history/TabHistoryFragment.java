package com.example.progetto_android.view.history;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.ShareDataViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabHistoryFragment extends Fragment {
    private Connector connector;
    private ShareDataViewModel sd;
    private Activity a;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        //necessario per poter modificare il menu
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tabbed_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //il ViewPager è lo spazio sotto le tab dove andranno inseriti i fragment
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        //istanzio adapter per creare i fragment delle tab
        TabHistoryAdapter tabAdapter = new TabHistoryAdapter(this);
        viewPager.setAdapter(tabAdapter);

        //prendo il layout con la view delle tab
        TabLayout tabs = view.findViewById(R.id.tabs);

        //tramite adapter creo tab e fragment che le riempirà
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> {
                    String titolo = tabAdapter.getPageTitle(position);

                    //inserisco il titolo delle tab
                    tab.setText(titolo);
                }
        ).attach();
    }

    //sovrascrivo il menu a seconda se l'utente è loggato o meno
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        sd.getLoggedIn().observeForever(data -> {
            if (data) {
                menu.clear();
                a.getMenuInflater().inflate(R.menu.menu_history, menu);
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
                NavHostFragment.findNavController(this).navigate(R.id.history_to_login);
            });
        }
        return super.onOptionsItemSelected(item);
    }

    //per sicurezza prendo l'activity e la inserisco in una variabile
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {
            this.a = (Activity) context;
        }

    }
}
