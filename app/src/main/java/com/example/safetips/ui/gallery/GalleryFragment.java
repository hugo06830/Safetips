package com.example.safetips.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safetips.MainActivity;
import com.example.safetips.R;
import com.example.safetips.Unconscioussness;
import com.example.safetips.assaultFragment;
import com.example.safetips.chokingFragment;
import com.example.safetips.databinding.FragmentGalleryBinding;
import com.example.safetips.earthquakeFragment;
import com.example.safetips.electricFragment;
import com.example.safetips.fireFragment;
import com.example.safetips.injuryFragment;
import com.example.safetips.terroristFragment;
import com.example.safetips.tsunamiFragment;
import com.google.android.material.search.SearchBar;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class GalleryFragment extends Fragment {



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

    private List<Emergency> emergencyList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        emergencyList = new ArrayList<>();
        emergencyList.add(new Emergency("unconsciousness"));
        emergencyList.add(new Emergency("injury"));
        emergencyList.add(new Emergency("choking"));
        emergencyList.add(new Emergency("fire"));
        emergencyList.add(new Emergency("electric"));
        emergencyList.add(new Emergency("assault"));
        emergencyList.add(new Emergency("terrorist"));
        emergencyList.add(new Emergency("earthquake"));
        emergencyList.add(new Emergency("tsunami"));


        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);
        SearchBarAdapter adapter = new SearchBarAdapter(emergencyList);
        RecyclerView recyclerView = rootView.findViewById(R.id.city_name_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);


        View emergency_list =  inflater.inflate(R.layout.item_list, container, false);

        // Getting all of the textview
        TextView unconsciousness = emergency_list.findViewById(R.id.unconsciousness);
        TextView injury = emergency_list.findViewById(R.id.injury);
        TextView choking = emergency_list.findViewById(R.id.choking);
        TextView fire = emergency_list.findViewById(R.id.fire);
        TextView electrical = emergency_list.findViewById(R.id.electric);
        TextView assault = emergency_list.findViewById(R.id.assault);
        TextView terrorist= emergency_list.findViewById(R.id.terrorist);
        TextView earthquake = emergency_list.findViewById(R.id.earthquake);
        TextView tsunami = emergency_list.findViewById(R.id.tsunami);

        //Assigning on click listener
        unconsciousness.setOnClickListener(textViewClickListener);
        injury.setOnClickListener(textViewClickListener);
        choking.setOnClickListener(textViewClickListener);
        fire.setOnClickListener(textViewClickListener);
        electrical.setOnClickListener(textViewClickListener);
        assault.setOnClickListener(textViewClickListener);
        terrorist.setOnClickListener(textViewClickListener);
        earthquake.setOnClickListener(textViewClickListener);
        tsunami.setOnClickListener(textViewClickListener);



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