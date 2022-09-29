package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travelworld.ejercicio11_permisos.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.travelworld.ejercicio11_permisos.databinding.FragmentHomeBinding binding = FragmentHomeBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}