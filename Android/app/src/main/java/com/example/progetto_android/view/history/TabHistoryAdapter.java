package com.example.progetto_android.view.history;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//Questa classe viene utilizzata per inserire nelle relative tab i fragment desiderati
public class TabHistoryAdapter extends FragmentStateAdapter {
    private final static String[] state = {"Prenotate", "Cancellate", "Effettuate"};

    public TabHistoryAdapter(Fragment fm) {
        super(fm);
    }

    public String getPageTitle(int position) {
        return state[position];
    }

    //metodo che mi istanzia il fragment con la view
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return HistoryFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return state.length;
    }
}
