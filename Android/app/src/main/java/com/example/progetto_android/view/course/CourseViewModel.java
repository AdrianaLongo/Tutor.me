package com.example.progetto_android.view.course;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

//intermezzo tra fragment e view/layout, permette di ottenere i dati contenuti nel layout
public class CourseViewModel extends ViewModel {
    //possono essere osservati tramite observer così da informare chi li sta osservando quando cambiano
    private final MutableLiveData<List<Course>> course;

    public CourseViewModel(){
        course = new MutableLiveData<>();
        course.setValue(new ArrayList<>());
    }

    public MutableLiveData<List<Course>> getCourse(){
        return course;
    }

    public void setCourse(List<Course> course){
        this.course.setValue(course);
    }
}
