package com.demo.materialdesigndemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.Holder> {

    private ArrayList<String> itemList;
    private Context context;
    private int selectedItemPosition = -1;
    public ItemAdapter(Context context, ArrayList<String> itemList, int selectedItemPosition) {
        this.context = context;
        this.itemList = itemList;
        this.selectedItemPosition = selectedItemPosition;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.item.setText(itemList.get(position));
        holder.item.setChecked(position == selectedItemPosition);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void filterList(ArrayList<String> filterItems) {
        this.itemList = filterItems;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {

        RadioButton item;
        public Holder(@NonNull View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.recycler_row_item);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemPosition = getAdapterPosition();
                    Toast.makeText(context, itemList.get(selectedItemPosition), Toast.LENGTH_SHORT).show();
                    notifyDataSetChanged();
                }
            });
        }
    }
}
