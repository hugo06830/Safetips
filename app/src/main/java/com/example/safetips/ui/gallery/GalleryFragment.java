package com.example.safetips.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

    public ArrayList<Emergency> emergencyArrayList = new ArrayList<Emergency>();

    private ListView listView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

        setupData();
        setupList(rootView);
        setUpOnClickListener();





        return rootView;
    }

    private void setUpOnClickListener() {

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Emergency selectedEmergency = (Emergency) listView.getItemAtPosition(i);
            Fragment selected = getClickedFragment(selectedEmergency.getName());
            openFragment(selected);
        }
    });
    }

    private void setupList(View rootView) {
        listView = rootView.findViewById(R.id.list_emergency);
        EmergencyAdapter adapter = new EmergencyAdapter(getActivity(), 0, emergencyArrayList);
        listView.setAdapter(adapter);

    }

    private void setupData() {
        //Create Emergencies
        Emergency unconsciousness = new Emergency("Unconsciousness", R.mipmap.unconsciousness_round );
        Emergency injury = new Emergency("Injury", R.mipmap.injury_round );
        Emergency choking = new Emergency("Choking", R.mipmap.terrorist_round );
        Emergency fire = new Emergency("Fire", R.mipmap.fire_round);
        Emergency electrical = new Emergency("Electrical", R.mipmap.electric_round );
        Emergency assault  = new Emergency("Assault", R.mipmap.assault_round );
        Emergency terrorist  = new Emergency("terrorist", R.mipmap.terroristt_round);
        Emergency earthquake  = new Emergency("Earthquake", R.mipmap.earthquake_round );
        Emergency tsunami  = new Emergency("Tsunami", R.mipmap.tsunami_round );

        //Adding them to the list
        emergencyArrayList.add(unconsciousness);
        emergencyArrayList.add(injury);
        emergencyArrayList.add(choking);
        emergencyArrayList.add(fire);
        emergencyArrayList.add(electrical);
        emergencyArrayList.add(assault);
        emergencyArrayList.add(terrorist);
        emergencyArrayList.add(earthquake);
        emergencyArrayList.add(tsunami);
    }

    private void openFragment(Fragment fragment) {
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_main, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private Fragment getClickedFragment(String emergencyName){

        switch (emergencyName.toLowerCase()){
            case "unconsciousness":
                return new Unconscioussness();
            case "injury":
                return new injuryFragment();
            case "choking":
                return new chokingFragment();
            case "fire":
                return new fireFragment();
            case "electrical":
                return new electricFragment();
            case "assault":
                return new assaultFragment();
            case "terrorist":
                return new terroristFragment();
            case "earthquake":
                return new earthquakeFragment();
            case "tsunami":
                return new tsunamiFragment();
        }

      return null;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}