package com.example.progetto_android.view.calendario;


import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SlotViewModel extends ViewModel {
    private MutableLiveData<Integer> mIndex = new MutableLiveData<>();
    private final MutableLiveData<List<Slot>> slots;

    public SlotViewModel(Context context){
        slots = new MutableLiveData<>();
        slots.setValue(new ArrayList<>());
    }

    public LiveData<List<Slot>> getSlot(){
        return this.slots;
    }

    public void setSlots(List<Slot> slots){this.slots.setValue(slots);}
    public void setmIndex(int i){
        mIndex.setValue(i);
    }
}