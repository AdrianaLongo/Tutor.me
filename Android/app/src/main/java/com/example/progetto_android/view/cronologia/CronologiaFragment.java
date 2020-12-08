package com.example.progetto_android.view.cronologia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.progetto_android.R;

import java.util.ArrayList;

public class CronologiaFragment extends Fragment {

    private CronologiaViewModel mViewModel;
    private PrenotazioneRecyclerAdapter mAdapter;

    public CronologiaFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.cronologia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.prenotazione);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CronologiaFactory factory = new CronologiaFactory(getActivity());
        mViewModel = new ViewModelProvider(this, factory).get(CronologiaViewModel.class);

        mAdapter = new PrenotazioneRecyclerAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
    }
}