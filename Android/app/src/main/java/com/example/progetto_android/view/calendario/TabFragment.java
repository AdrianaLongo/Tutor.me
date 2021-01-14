package com.example.progetto_android.view.calendario;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.progetto_android.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

// questo fragment si occupa di gestire le tab
public class TabFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.tabbed_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //private final static String KEY_INDEX = "KEY_INDEX";
        //private SlotViewModel slotViewModel;
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        TabAdapter tabAdapter = new TabAdapter(this);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> {
                    String titolo = tabAdapter.getPageTitle(position);

                    //TODO eliminare una volta finito tutto
                    Log.i("58 TabFragment", "Sono dentro i titoli" + titolo);

                    tab.setText(titolo);

                }
        ).attach();

        //TODO capire perché non fa cambiare tab col colore
       /* tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                switch(tab.getPosition()){
                    case 0:
                        tabs.getTabAt(tabAdapter.getPosition()).getIcon().setColorFilter(getResources().getColor(android.R.color.black,null), PorterDuff.Mode.SRC_IN);
                }

                tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.black,null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.black,null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.black,null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(4).getIcon().setColorFilter(getResources().getColor(android.R.color.black,null), PorterDuff.Mode.SRC_IN);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabs.getTabAt(0).getIcon().setColorFilter(getResources().getColor(android.R.color.white, null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(1).getIcon().setColorFilter(getResources().getColor(android.R.color.white, null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(2).getIcon().setColorFilter(getResources().getColor(android.R.color.white, null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(3).getIcon().setColorFilter(getResources().getColor(android.R.color.white, null), PorterDuff.Mode.SRC_IN);
                tabs.getTabAt(4).getIcon().setColorFilter(getResources().getColor(android.R.color.white, null), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
    }
}