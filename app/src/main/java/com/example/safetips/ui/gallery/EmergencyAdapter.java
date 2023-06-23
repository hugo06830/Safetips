package com.example.safetips.ui.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.safetips.R;

import java.util.List;

public class EmergencyAdapter extends ArrayAdapter<Emergency> {

    public EmergencyAdapter(Context context, int ressource, List<Emergency> emergencyList)
    {
        super(context, ressource, emergencyList);
    }



    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        Emergency emergency = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.emergency_cell, parent, false);

        }
        TextView text = convertView.findViewById(R.id.emergencyName);
        ImageView icon = convertView.findViewById(R.id.emergencyImage);

        text.setText(emergency.getName());
        icon.setImageResource(emergency.getImage());

        return convertView;
    }
}
