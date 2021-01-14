package com.example.progetto_android.view.calendario;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.progetto_android.R;

import java.util.ArrayList;
import java.util.List;

//TODO provare a ottenere solo i giorni interessati già al momento della richiesta nel fragment nel metodo onResume mediante l'utilizzo dell'index, controllare quando viene istanziato se prima o dopo l'onResume
public class SlotRecyclerAdapter extends RecyclerView.Adapter<SlotRecyclerAdapter.ViewHolder> {
    //private final List<List<Slot>> slots;
    private List<Slot> slots;
    private final LayoutInflater inflater;
   // private final int selectedPosition = -1;
    private final int index;

    public SlotRecyclerAdapter(Context context, int index, List<Slot> slots) {
        this.inflater = LayoutInflater.from(context);
        /*if (slot != null)
            this.slots = getFull(slot);
        else
            this.slots = null;*/
        this.index = index;
        this.slots = slots;

        //TODO eliminare una volta finito tutto
        Log.i("23 SlotRecyclerAdapter", "Slot ottenuti" + this.slots.toString());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.slot_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Slot slot = null;
        position = 0;
        Log.i("51 SlotAdapter", "slot in position: " + position + " " + slots.get(position).toString());
        //TODO continuare, inserire i giorni interessati tramite l'index
        /*switch (index){
            case 0:
                if(slots.get(position).getSezione().contains("LUN"))
                    slot = slots.get(position);
                //TODO eliminare una volta finito tutto
                Log.i("52 SlotRecycler", slots.get(position).toString());
                break;
            case 1:
                if(slots.get(position).getSezione().contains("MAR"))
                    slot = slots.get(position);
                break;
            case 2:
                if(slots.get(position).getSezione().contains("MER"))
                    slot = slots.get(position);
                break;
            case 3:
                if(slots.get(position).getSezione().contains("GIO"))
                    slot = slots.get(position);
                break;
            case 4:
                if(slots.get(position).getSezione().contains("VEN"))
                    slot = slots.get(position);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + index);
        }
        if (slot != null) {
            //TODO eliminare una volta finito tutto
            Log.i("76 SlotRecycler", slot.toString() + " " + index);
            slots = consume(slots);
            holder.slotView.setText(slot.getSezione());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO pensare a come fare comparire una finestrella popup dove c'è scritto un recap della prenotazione e dove dare la conferma
                }
            });
        }*/
    }

    @Override
    public int getItemCount() {
        return slots.size();
    }

    void setClickListener(ItemClickListener itemClickListener) {
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView slotView;

        ViewHolder(View itemView) {
            super(itemView);
            slotView = itemView.findViewById(R.id.slot);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "click: " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }

        public void setData(List<Slot> newSlots) {
            if (slots != null) {
                PostDiffCallback postDiffCallback = new PostDiffCallback(slots, newSlots);
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(postDiffCallback);
                slots.clear();
                slots.addAll(newSlots);
            }
        }
    }

    public static class PostDiffCallback extends DiffUtil.Callback {
        private final List<Slot> oldPosts, newPosts;

        public PostDiffCallback(List<Slot> oldPosts, List<Slot> newPosts) {
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
            return oldPosts.get(oldItemPosition).getSezione() == newPosts.get(newItemPosition).getSezione();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldPosts.get(oldItemPosition).equals(newPosts.get(newItemPosition));
        }
    }

    // elimino il primo elemento già letto dalla lista degli orari
    private List<Slot> consume(List<Slot> slots) {
        ArrayList<Slot> reduce = new ArrayList<Slot>();
        reduce.clear();
        for(int i = 1; i < slots.size(); i++){
            reduce.add(slots.get(i));
        }
        return reduce;
    }
}
