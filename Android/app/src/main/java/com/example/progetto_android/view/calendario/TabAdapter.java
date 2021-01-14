package com.example.progetto_android.view.calendario;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
// Questa classe serve per gestire le tab
public class TabAdapter extends FragmentStateAdapter {

    private final static String[] titoli = {"Lun", "Mar", "Mer", "Gio", "Ven"};

    public TabAdapter(Fragment fm) {
        super(fm);
    }

    public String getPageTitle(int position) {
        return titoli[position];
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return TimeSlotDayFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return titoli.length;
    }
}