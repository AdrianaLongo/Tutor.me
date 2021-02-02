package com.example.progetto_android.view.tutors;

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

//questa classe serve per impostare la RecyclerView
public class TutorRecyclerAdapter extends RecyclerView.Adapter<TutorRecyclerAdapter.ViewHolder> {
    private List<Tutor> profs;
    private final LayoutInflater inflater;
    private int selectedPosition = -1;      //variabile di controllo per cercare cos'è selezionato

    public TutorRecyclerAdapter(Context context, List<Tutor> profs) {
        this.inflater = LayoutInflater.from(context);
        this.profs = profs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prof_row, parent, false);
        return new ViewHolder(view);
    }

    //questo metodo viene evocato per ogni riga che viene creata all'interno della view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //position è un parametro che indica la posizione della riga che viene creata all'interno della recyclerView
        Tutor prof = profs.get(position);

        //inserisco i dati ottenuti nelle relative TextView
        holder.name_prof.setText(prof.getCognome());
        holder.surname_prof.setText(" " + prof.getNome());

        //collego un listener alla textView così che l'utente per comodità dovunque pigi selezionerà l'opzione
        holder.itemView.setOnClickListener(v -> {
            holder.check.setChecked(true);
            selectedPosition = position;

            //inserisco la materia scelta nella prenotazione che poi verrà inviata alla fine delle scelte
            GlobalValue.setProf(getItem(selectedPosition));
            notifyDataSetChanged();
        });
        //tramite questo controllo permetto che venga selezionata solo un professore alla volta
        holder.check.setChecked(selectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return profs.size();
    }

    public Tutor getItem(int position) {
        return profs.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name_prof;
        TextView surname_prof;
        private final CheckBox check;

        ViewHolder(View itemView) {
            super(itemView);
            name_prof = itemView.findViewById(R.id.nome_prof);
            surname_prof = itemView.findViewById(R.id.cognome_prof);
            check = itemView.findViewById(R.id.select);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }

        //questo metodo serve per controllare quando l'utente scorre la pagina per aggiornare i professori
        public void setData(List<Tutor> newProfs) {
            if (profs != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(profs, newProfs);
                DiffUtil.calculateDiff(postDiffCallback);
                profs.clear();
                profs.addAll(newProfs);
            } else {
                profs = newProfs;
            }
        }
    }

    public static class PostDiffCallback extends DiffUtil.Callback {
        private final List<Tutor> oldPosts, newPosts;

        public PostDiffCallback(List<Tutor> oldPosts, List<Tutor> newPosts) {
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
            return oldPosts.get(oldItemPosition).getProf() == newPosts.get(newItemPosition).getProf();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }
}
