package com.example.progetto_android.view.calendar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

//Questa classe viene utilizzata per inserire nelle relative tab i fragment desiderati
public class TabSlotAdapter extends FragmentStateAdapter {
    private final static String[] days = {"Lun", "Mar", "Mer", "Gio", "Ven"};

    public TabSlotAdapter(Fragment fm) {
        super(fm);
    }

    public String getPageTitle(int position) {
        return days[position];
    }

    //metodo che mi istanzia il fragment con la view
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return SlotDayFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return days.length;
    }
}