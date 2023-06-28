package com.example.safetips.ui.slideshow;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import com.google.android.gms.maps.OnMapReadyCallback;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;

import com.example.safetips.R;
import com.example.safetips.databinding.FragmentSlideshowBinding;
import com.google.android.gms.maps.model.MarkerOptions;


public class SlideshowFragment extends Fragment implements OnMapReadyCallback {

    private FragmentSlideshowBinding binding;
    private GoogleMap Lmap;
    private static final  int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        //Get user location and initialise map
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireActivity());

        return root;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    //Map manipulation done here
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Lmap = googleMap;

        // Check if the location permission is granted
        if (hasLocationPermission()) {
            // If permission is granted, move the camera to the user's location
            moveMapToUserLocation();
            // Add a red dot at the user's position
            addCurrentUserLocationMarker();

        } else {
            // If permission is not granted, request the permission
            requestLocationPermission();
        }

    }

    @SuppressLint("MissingPermission")
    private void addCurrentUserLocationMarker() {
        // Check if the location permission is granted
        if (hasLocationPermission()) {
            // Add a marker at the user's position
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(requireActivity(), location -> {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            Lmap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                                    .title("Current Location"));
                        }
                    });
        }
    }

    private boolean hasLocationPermission() {
        int permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                requireActivity(),
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE
        );
    }

    @SuppressLint("MissingPermission")
    private void moveMapToUserLocation() {
        // Check if the location permission is granted
        if (hasLocationPermission()) {
            // Move the map camera to the user's location
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(requireActivity(), location -> {
                        if (location != null) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            Lmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                        }
                    });
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, move the camera to the user's location
                moveMapToUserLocation();
            } else {
                // Permission denied, handle accordingly (e.g., show a message, disable location-related functionality)
            }
        }
    }

}