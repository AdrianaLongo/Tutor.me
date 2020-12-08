package com.example.progetto_android.view.corsi;

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

public class CorsiRecyclerAdapter extends RecyclerView.Adapter<CorsiRecyclerAdapter.ViewHolder> {
    private List<Corso> courses;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;

    public CorsiRecyclerAdapter(Context context, List<Corso> courses) {
        this.inflater = LayoutInflater.from(context);
        this.courses = courses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.course_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Corso course = courses.get(position);
        holder.corso.setText(course.getCorso());
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public Corso getItem(int position){//(String nomeCorso){
        /*Corso res = null;
        for(Corso corso:courses){
            if(corso.getCorso().equals(nomeCorso))
                res = corso;
        }
        return res;*/
        return courses.get(position);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView corso;

        ViewHolder(View itemView) {
            super(itemView);
            corso = itemView.findViewById(R.id.corso);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "click: " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }

        public void setData(List<Corso> newCorsi) {
            if (courses != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(courses, newCorsi);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
                courses.clear();
                courses.addAll(newCorsi);
            } else {
                courses = newCorsi;
            }
        }
    }

    public class PostDiffCallback extends DiffUtil.Callback{
        private final List<Corso> oldPosts, newPosts;

        public PostDiffCallback(List<Corso> oldPosts, List<Corso> newPosts){
            this.oldPosts = oldPosts;
            this.newPosts = newPosts;
        }

        @Override
        public int getOldListSize(){
            return oldPosts.size();
        }

        @Override
        public int getNewListSize(){
            return newPosts.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition){
            return oldPosts.get(oldItemPosition).getCorso() == newPosts.get(newItemPosition).getCorso();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition){
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }
}
