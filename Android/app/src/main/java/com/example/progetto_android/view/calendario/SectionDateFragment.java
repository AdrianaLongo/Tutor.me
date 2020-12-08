package com.example.progetto_android.view.calendario;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.progetto_android.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

// Qua è dove si andrà a riempire la schermata con le schermate dei corsi
public class SectionDateFragment extends Fragment {
    private static String KEY_INDEX = "com.ium.unito.androidtabbed.KEY_INDEX";
    private ViewPager2 viewPager;

    public static SectionDateFragment newInstance() {
        SectionDateFragment fragment = new SectionDateFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public static SectionDateFragment create(int position) {
        SectionDateFragment result = new SectionDateFragment();
        Bundle args = new Bundle();
        args.putString(KEY_INDEX, String.valueOf(position));
        result.setArguments(args);
        return result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tabbed_layout, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> {
                    String titolo = sectionsPagerAdapter.getPageTitle(position);
                    tab.setText(titolo);
                }
        ).attach();
    }


}