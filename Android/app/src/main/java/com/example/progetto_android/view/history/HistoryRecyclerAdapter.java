package com.example.progetto_android.view.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.view.calendar.Slot;

import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private List<Book> book;
    private LayoutInflater inflater;
    private ItemClickListener mClickListener;
    private int selectedPosition = -1;      //variabile di controllo per cercare cos'è selezionato
    private int index;
    private Context context;
    private Connector connector;

    public HistoryRecyclerAdapter(Context context, List<Book> book, int index) {
        this.inflater = LayoutInflater.from(context);
        this.book = book;
        this.index = index;
        this.context = context;
        this.connector = Connector.getInstance(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book elem = book.get(position);
        Slot slot = new Slot(elem.getSlot());
        holder.nprof.setText(String.format("%s ", elem.getNomeDocente()));
        holder.cprof.setText(String.format("%s ", elem.getCognomeDocente()));
        holder.materia.setText(String.format("%s ", elem.getNomeCorso()));
        //tramite il metodo per convertire gli slot nella classe slot ottengo giorno e ora
        holder.orario.setText(String.format("%s ", slot.getDay(slot.getSezione()) + " " + slot.getHour()));

        if(elem.getStato().equals("0")) {
            holder.menu.setVisibility(View.VISIBLE);
            holder.menu.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context, holder.menu);
                    popup.inflate(R.menu.menu_state_book);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.action_done:
                                    GlobalValue.setIdPrenotazione(book.get(position).getIdPrenotazione() + "");
                                    connector.getBookRep().doneBook(res -> {
                                        Toast.makeText(context, res.getMessage(), Toast.LENGTH_LONG).show();
                                    });
                                    return true;
                                case R.id.action_delete:
                                    GlobalValue.setIdPrenotazione(book.get(position).getIdPrenotazione() + "");
                                    connector.getBookRep().deleteBook(res -> {
                                        Toast.makeText(context, res.getMessage(), Toast.LENGTH_LONG).show();
                                    });
                                    return true;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });
        }
    }

    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    @Override
    public int getItemCount() {
        return book.size();
    }

    public Book getItem(int position) {
        return book.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nprof;
        TextView cprof;
        TextView materia;
        TextView orario;
        TextView menu;

        public ViewHolder(View itemView) {
            super(itemView);
            nprof = itemView.findViewById(R.id.nprof);
            cprof = itemView.findViewById(R.id.cprof);
            materia = itemView.findViewById(R.id.materia);
            orario = itemView.findViewById(R.id.orario);
            menu = itemView.findViewById(R.id.textViewOptions);

            itemView.setOnClickListener(this);
        }

        public void onClick(View view) {
        }

        public void setData(List<Book> newBook) {
            if (book != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(book, newBook);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
                book.clear();
                book.addAll(newBook);
            } else {
                book = newBook;
            }
        }
    }

    public class PostDiffCallback extends DiffUtil.Callback {
        private final List<Book> oldPosts, newPosts;

        public PostDiffCallback(List<Book> oldPosts, List<Book> newPosts) {
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
            return oldPosts.get(oldItemPosition).getNomeDocente() == newPosts.get(newItemPosition).getNomeDocente() &&
                    oldPosts.get(oldItemPosition).getNomeCorso() == newPosts.get(newItemPosition).getNomeCorso() &&
                    oldPosts.get(oldItemPosition).getSlot() == newPosts.get(newItemPosition).getSlot();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }
}
