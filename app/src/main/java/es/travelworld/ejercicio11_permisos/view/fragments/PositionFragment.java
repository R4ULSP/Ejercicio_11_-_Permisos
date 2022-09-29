package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelworld.ejercicio11_permisos.databinding.FragmentPositionBinding;

import es.travelworld.ejercicio11_permisos.domain.References;


public class PositionFragment extends Fragment {

    private int position;

    public static PositionFragment newInstance(int position) {
        PositionFragment positionFragment = new PositionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(References.KEY_POSITION, position);
        positionFragment.setArguments(bundle);
        return positionFragment;
    }

    public PositionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            position = getArguments().getInt(References.KEY_POSITION);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        com.travelworld.ejercicio11_permisos.databinding.FragmentPositionBinding binding = FragmentPositionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        position = position + 1;
        binding.position.setText("" + position);

        return view;
    }
}