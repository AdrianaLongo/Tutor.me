package com.example.progetto_android.view.calendario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.progetto_android.R;
import com.google.android.material.tabs.TabLayout;

// Qui viene implementata la parte basse dove sono contenuti gli orari disponibili per le materie
public class CalendarioSectionFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static CalendarioSectionFragment newInstance(int index) {
        CalendarioSectionFragment fragment = new CalendarioSectionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        TabLayout tab = requireActivity().findViewById(R.id.tabs);
        // recupera dal Fragment il titolo, qui solo per prova
        //Objects.requireNonNull(tab.getTabAt(index)).setText("TITOLO da" + index);     //modifica il nome delle tab una volta che ci si passa

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.calendariotabbed_view, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        // 3 stringhe sotto da cancellare, inserire recycler view model
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return root;
    }

}