package com.example.progetto_android.view.course;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;
import com.example.progetto_android.globals.GlobalValue;

import java.util.List;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder> {
    private List<Course> courses;
    private final LayoutInflater inflater;
    private int selectedPosition = -1;      //variabile di controllo per cercare cos'è selezionato

    public CourseRecyclerAdapter(Context context, List<Course> courses) {
        this.inflater = LayoutInflater.from(context);
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.course_row, parent, false);
        return new ViewHolder(view);
    }

    //questo metodo viene evocato per ogni riga che viene creata all'interno della view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //position è un parametro che indica la posizione della riga che viene creata all'interno della recyclerView
        Course course = courses.get(position);

        //inserisco il nome del corso ottenuto
        holder.courseView.setText(course.getNomeCorso());

        //collego un listener alla textView così che l'utente per comodità dovunque pigi selezionerà l'opzione
        holder.itemView.setOnClickListener(v -> {

            final boolean newValue = !holder.check.isChecked();

            holder.check.setChecked(newValue);
            selectedPosition = position;

            //inserisco la materia scelta nella prenotazione che poi verrà inviata alla fine delle scelte
            GlobalValue.setCourse(getItem(selectedPosition));
            notifyDataSetChanged();
        });
        //tramite questo controllo permetto che venga selezionata solo un corso alla volta
        holder.check.setChecked(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public Course getItem(int position) {
        return courses.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView courseView;
        private final CheckBox check;

        ViewHolder(View itemView) {
            super(itemView);
            courseView = itemView.findViewById(R.id.corso);
            check = itemView.findViewById(R.id.check);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

        //questo metodo serve per controllare quando l'utente scorre la pagina per aggiornare i corsi
        public void setData(List<Course> newCorsi) {
            if (courses != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(courses, newCorsi);
                DiffUtil.calculateDiff(postDiffCallback);
                courses.clear();
                courses.addAll(newCorsi);
            } else {
                courses = newCorsi;
            }
        }
    }

    public static class PostDiffCallback extends DiffUtil.Callback {
        private final List<Course> oldPosts, newPosts;

        public PostDiffCallback(List<Course> oldPosts, List<Course> newPosts) {
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize() {
            return oldPosts.size();
        }

        @Override
        public int getNewListSize() {
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getNomeCorso() == newPosts.get(newItemPosition).getNomeCorso();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }
}
