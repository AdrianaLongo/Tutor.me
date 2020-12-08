package com.example.progetto_android.view.professori;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.progetto_android.R;

import java.util.ArrayList;

public class SectionProfFragment extends Fragment {

    private ProfViewModel mViewModel;
    private ProfRecyclerAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_section_prof, container, false);
    }

    @Override
    public void onViewCreated(@Nullable View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.profs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ProfFactory factory = new ProfFactory(getActivity());
        mViewModel = new ViewModelProvider(this, factory).get(ProfViewModel.class);

        mAdapter = new ProfRecyclerAdapter(getActivity(), new ArrayList<>());
        recyclerView.setAdapter(mAdapter);

        view.findViewById(R.id.button_prof).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SectionProfFragment.this)
                        .navigate(R.id.menu_prof_to_menu_calendar);
            }
        });
    }

}