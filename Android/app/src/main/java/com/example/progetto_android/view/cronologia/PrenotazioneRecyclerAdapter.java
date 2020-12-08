package com.example.progetto_android.view.cronologia;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progetto_android.R;

import java.util.List;

public class PrenotazioneRecyclerAdapter extends RecyclerView.Adapter<PrenotazioneRecyclerAdapter.ViewHolder> {

    private List<Prenotazione> prenotazioni;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;

    public PrenotazioneRecyclerAdapter(Context context, List<Prenotazione> prenotazione) {
        this.inflater = LayoutInflater.from(context);
        this.prenotazioni = prenotazione;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cronologia_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prenotazione elem = prenotazioni.get(position);
        holder.prof.setText(elem.getDocente());
        holder.materia.setText(elem.getMateria());
        holder.orario.setText(elem.getSlot());
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return prenotazioni.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView prof;
        TextView materia;
        TextView orario;

        public ViewHolder(View itemView) {
            super(itemView);
            prof = (TextView) itemView.findViewById(R.id.prof);
            materia = (TextView) itemView.findViewById(R.id.materia);
            orario = (TextView) itemView.findViewById(R.id.orario);
            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
            Toast.makeText(view.getContext(), "click: " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }

        public void setData(List<Prenotazione> newPrenotazioni) {
            if (prenotazioni != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(prenotazioni, newPrenotazioni);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
                prenotazioni.clear();
                prenotazioni.addAll(newPrenotazioni);
            } else {
                prenotazioni = newPrenotazioni;
            }
        }
    }

    public class PostDiffCallback extends DiffUtil.Callback {
        private final List<Prenotazione> oldPosts, newPosts;

        public PostDiffCallback(List<Prenotazione> oldPosts, List<Prenotazione> newPosts) {
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
            return oldPosts.get(oldItemPosition).getDocente() == newPosts.get(newItemPosition).getDocente() &&
                    oldPosts.get(oldItemPosition).getMateria() == newPosts.get(newItemPosition).getMateria() &&
                    oldPosts.get(oldItemPosition).getSlot() == newPosts.get(newItemPosition).getSlot();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).getDocente().equals(newPosts.get(newItemPosition).getDocente()) &&
                    oldPosts.get(oldItemPosition).getMateria().equals(newPosts.get(newItemPosition).getMateria()) &&
                    oldPosts.get(oldItemPosition).getSlot().equals(newPosts.get(newItemPosition).getSlot());
        }
    }
}
