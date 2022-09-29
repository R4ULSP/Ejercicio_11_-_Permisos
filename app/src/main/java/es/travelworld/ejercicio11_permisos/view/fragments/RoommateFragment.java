package es.travelworld.ejercicio11_permisos.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travelworld.ejercicio11_permisos.databinding.FragmentRoommateBinding;

public class RoommateFragment extends Fragment {

    private FragmentRoommateBinding binding;

    private OnClickItemRoommateFragment listener;

    public interface OnClickItemRoommateFragment {
        void roommateLoginButton();
    }

    public RoommateFragment() {
        // Required empty public constructor
    }


    public static RoommateFragment newInstance() {

        return new RoommateFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRoommateBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setListeners();

        return view;
    }

    private void setListeners() {
        binding.matchButtonLogin.setOnClickListener(view -> listener.roommateLoginButton());
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof RoommateFragment.OnClickItemRoommateFragment) {
            listener = (RoommateFragment.OnClickItemRoommateFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}