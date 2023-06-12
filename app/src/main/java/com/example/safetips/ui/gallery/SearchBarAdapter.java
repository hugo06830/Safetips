package com.example.safetips.ui.gallery;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // No need to inflate a new view or use a ViewHolder, as the view is already displayed
        return new ViewHolder(new View(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // No need to bind data, as the view is already displayed
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}



