package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelworld.ejercicio11_permisos.databinding.FragmentOnboardingBinding;

public class OnBoardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;

    public OnBoardingFragment() {
        // Required empty public constructor
    }


    public static OnBoardingFragment newInstance() {
        return new OnBoardingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setListeners();

        return view;
    }

    private void setListeners() {
        binding.buttonNext.setOnClickListener(view -> nextPage());

    }

    private void nextPage() {
        DestinationFragment destinationFragment = ((DestinationFragment)OnBoardingFragment.this.getParentFragment());
        if(destinationFragment != null) {
            destinationFragment.nextPage();
        }
    }


}