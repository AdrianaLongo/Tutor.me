package com.example.progetto_android.view.calendar;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.progetto_android.view.history.Book;

import java.util.ArrayList;
import java.util.List;

//intermezzo tra fragment e view/layout, permette di ottenere i dati contenuti nel layout
public class SlotViewModel extends ViewModel {
    //possono essere osservati tramite observer così da informare chi li sta osservando quando cambiano
    private final MutableLiveData<List<Slot>> slots;
    private final MutableLiveData<List<Book>> book;

    public SlotViewModel(){
        slots = new MutableLiveData<>();
        book = new MutableLiveData<>();
        slots.setValue(new ArrayList<>());
        book.setValue(new ArrayList<>());
    }

    public LiveData<List<Slot>> getSlots(){
        return this.slots;
    }

    public LiveData<List<Book>> getBook(){
        return this.book;
    }
    public void setSlots(List<Slot> slots){this.slots.setValue(slots);}

    public void setBook(List<Book> book){
        this.book.setValue(book);
    }
}