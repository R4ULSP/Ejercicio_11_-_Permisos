package es.travelworld.ejercicio11_permisos.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.travelworld.ejercicio11_permisos.databinding.FragmentMatchBinding;

public class MatchFragment extends Fragment {

    private FragmentMatchBinding binding;

    private OnClickItemMatchFragment listener;

    public interface OnClickItemMatchFragment {
        void matchSkipButton();
    }

    public MatchFragment() {
        // Required empty public constructor
    }


    public static MatchFragment newInstance() {
        return new MatchFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatchBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setListeners();
        return view;
    }

    private void setListeners() {
        binding.matchButtonNext.setOnClickListener(view -> nextPage());
        binding.matchButtonSkip.setOnClickListener(view -> listener.matchSkipButton());
    }

    private void nextPage() {
        DestinationFragment destinationFragment = ((DestinationFragment)MatchFragment.this.getParentFragment());
        if(destinationFragment != null) {
            destinationFragment.nextPage();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof MatchFragment.OnClickItemMatchFragment) {
            listener = (MatchFragment.OnClickItemMatchFragment) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}