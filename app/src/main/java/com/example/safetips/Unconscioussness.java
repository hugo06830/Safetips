package com.example.safetips;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Unconscioussness extends Fragment {


    public Unconscioussness() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View toolbarView = inflater.inflate(R.layout.app_bar_main, container, false);
        Toolbar toolbar = toolbarView.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        View rootView = inflater.inflate(R.layout.fragment_unconscioussness, container, false);
        Button returnButton = rootView.findViewById(R.id.return_button);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        return rootView;
    }
}