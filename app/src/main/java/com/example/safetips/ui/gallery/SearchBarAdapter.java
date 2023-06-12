package com.example.safetips.ui.gallery;

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

    private List<Emergency> emergencyList;
    private List<Emergency> filteredList;

    // Constructor
    public SearchBarAdapter(List<Emergency> emergencyList) {
        this.emergencyList = emergencyList;
        this.filteredList = new ArrayList<>(emergencyList);
    }

    // ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView emergencyNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            emergencyNameTextView = itemView.findViewById(R.id.unconsciousness);

            // Set click listener for each item
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle item click event here
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Emergency emergency = filteredList.get(position);
                        // Open the corresponding fragment or perform necessary action
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Emergency emergency = filteredList.get(position);
        holder.emergencyNameTextView.setText(emergency.getName());
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new EmergencyFilter();
    }

    private class EmergencyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            String query = constraint.toString().toLowerCase();

            if (query.isEmpty()) {
                results.values = emergencyList;
            } else {
                List<Emergency> filteredResults = new ArrayList<>();

                for (Emergency emergency : emergencyList) {
                    if (emergency.getName().toLowerCase().contains(query)) {
                        filteredResults.add(emergency);
                    }
                }

                results.values = filteredResults;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredList = (List<Emergency>) results.values;
            notifyDataSetChanged();
        }
    }
}

