package com.example.progetto_android.view.professori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;

import java.util.List;

public class ProfRecyclerAdapter extends RecyclerView.Adapter<ProfRecyclerAdapter.ViewHolder> {
    private List<Professore> profs;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;

    public ProfRecyclerAdapter(Context context, List<Professore> profs) {
        this.inflater = LayoutInflater.from(context);
        this.profs = profs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.prof_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Professore prof = profs.get(position);
        holder.nome_prof.setText(prof.getCognome());
        holder.cognome_prof.setText(prof.getNome());
    }

    @Override
    public int getItemCount() {
        return profs.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nome_prof;
        TextView cognome_prof;

        ViewHolder(View itemView) {
            super(itemView);
            nome_prof = (TextView) itemView.findViewById(R.id.nome_prof);
            cognome_prof = (TextView) itemView.findViewById(R.id.cognome_prof);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "click: " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }

        public void setData(List<Professore> newProfs) {
            if (profs != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(profs, newProfs);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
                profs.clear();
                profs.addAll(newProfs);
            } else {
                profs = newProfs;
            }
        }
    }

    public class PostDiffCallback extends DiffUtil.Callback {
        private final List<Professore> oldPosts, newPosts;

        public PostDiffCallback(List<Professore> oldPosts, List<Professore> newPosts) {
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
