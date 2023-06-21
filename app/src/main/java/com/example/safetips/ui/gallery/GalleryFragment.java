package com.example.safetips.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetips.R;
import com.example.safetips.Unconscioussness;
import com.example.safetips.assaultFragment;
import com.example.safetips.chokingFragment;

import com.example.safetips.earthquakeFragment;
import com.example.safetips.electricFragment;
import com.example.safetips.fireFragment;
import com.example.safetips.injuryFragment;
import com.example.safetips.terroristFragment;
import com.example.safetips.tsunamiFragment;

import java.util.ArrayList;
import java.util.List;


public class GalleryFragment extends Fragment {

    private List<String> itemList;
    private SearchBarAdapter itemAdapter;
    private RecyclerView recyclerView;
    private SearchView searchView;

    private String query = "";



    private  View.OnClickListener textViewClickListener = v -> {
        int textViewId = v.getId();

        // Perform the action based on the clicked TextView
        if (textViewId == R.id.unconsciousness) {
            openFragment(new Unconscioussness());
        } else if (textViewId == R.id.injury) {
            openFragment(new injuryFragment());
        } else if (textViewId == R.id.choking) {
            openFragment(new chokingFragment());
        } else if (textViewId == R.id.fire) {
            openFragment(new fireFragment());
        } else if (textViewId == R.id.electric) {
            openFragment(new electricFragment());
        } else if (textViewId == R.id.assault) {
            openFragment(new assaultFragment());
        } else if (textViewId == R.id.terrorist) {
            openFragment(new terroristFragment());
        } else if (textViewId == R.id.earthquake) {
            openFragment(new earthquakeFragment());
        } else if (textViewId == R.id.tsunami) {
            openFragment(new tsunamiFragment());
        }
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        itemList = new ArrayList<>();
        itemList.add("unconsciousness");
        itemList.add("injury");
        itemList.add("choking");
        itemList.add("fire");
        itemList.add("electric");
        itemList.add("assault");
        itemList.add("terrorist");
        itemList.add("earthquake");
        itemList.add("tsunami");

        recyclerView = rootView.findViewById(R.id.city_name_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        itemAdapter = new SearchBarAdapter(itemList, query);
        itemAdapter.setHasStableIds(true);
        recyclerView.setAdapter(itemAdapter);

        // Initialize the SearchView
        searchView = rootView.findViewById(R.id.search_bar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                query = newText;
                itemAdapter.getFilter().filter(newText);
                return true;
            }
        });




        return rootView;
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}