package com.example.safetips.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetips.R;

import java.util.ArrayList;
import java.util.List;

public class SearchBarAdapter extends RecyclerView.Adapter<SearchBarAdapter.ViewHolder> implements Filterable {

    private List<String> itemList;
    private List<String> filteredItemList;

    private String query;

    public SearchBarAdapter( List<String> itemList, String query) {

        this.itemList = itemList;
        this.filteredItemList = new ArrayList<>(itemList);
        this.query = query;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String item = filteredItemList.get(position);

        // Set the text for the corresponding TextView in the item layout
        int id = holder.itemView.getId();
        if (id == R.id.unconsciousness) {
            holder.unconsciousnessTextView.setText(item);
        } else if (id == R.id.injury) {
            holder.injuryTextView.setText(item);
        } else if (id == R.id.choking) {
            holder.chokingTextView.setText(item);
        } else if (id == R.id.fire) {
            holder.fireTextView.setText(item);
        } else if (id == R.id.electric) {
            holder.electricTextView.setText(item);
        } else if (id == R.id.assault) {
            holder.assaultTextView.setText(item);
        } else if (id == R.id.terrorist) {
            holder.terroristTextView.setText(item);
        } else if (id == R.id.earthquake) {
            holder.earthquakeTextView.setText(item);
        } else if (id == R.id.tsunami) {
            holder.tsunamiTextView.setText(item);
        }
    }


    @Override
    public int getItemCount() {
        return filteredItemList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();
                FilterResults results = new FilterResults();

                if (filterPattern.isEmpty()) {
                    results.values = itemList;
                    results.count = itemList.size();
                } else {
                    List<String> filteredList = new ArrayList<>();
                    for (String item : itemList) {
                        if (item.toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                    results.values = filteredList;
                    results.count = filteredList.size();
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredItemList = (List<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView unconsciousnessTextView;
        TextView injuryTextView;
        TextView chokingTextView;
        TextView fireTextView;
        TextView electricTextView;
        TextView assaultTextView;
        TextView terroristTextView;
        TextView earthquakeTextView;
        TextView tsunamiTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            unconsciousnessTextView = itemView.findViewById(R.id.unconsciousness);
            injuryTextView = itemView.findViewById(R.id.injury);
            chokingTextView = itemView.findViewById(R.id.choking);
            fireTextView = itemView.findViewById(R.id.fire);
            electricTextView = itemView.findViewById(R.id.electric);
            assaultTextView = itemView.findViewById(R.id.assault);
            terroristTextView = itemView.findViewById(R.id.terrorist);
            earthquakeTextView = itemView.findViewById(R.id.earthquake);
            tsunamiTextView = itemView.findViewById(R.id.tsunami);
        }
    }
}



