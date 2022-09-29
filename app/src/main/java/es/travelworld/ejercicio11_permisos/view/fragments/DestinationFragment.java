package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.travelworld.ejercicio11_permisos.databinding.FragmentDestinationBinding;

import es.travelworld.ejercicio11_permisos.domain.References;

public class DestinationFragment extends Fragment {

    private FragmentDestinationBinding binding;

    public DestinationFragment() {
        // Required empty public constructor
    }

    public static DestinationFragment newInstance() {
        return new DestinationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDestinationBinding.inflate(inflater, container, false);

        StartDestinationFragmentStateAdapter fragmentStateAdapter = new StartDestinationFragmentStateAdapter(this);
        binding.viewPager.setAdapter(fragmentStateAdapter);

        return binding.getRoot();
    }

    public void nextPage(){
        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
    }

    private class StartDestinationFragmentStateAdapter extends FragmentStateAdapter {
        public StartDestinationFragmentStateAdapter(DestinationFragment destinationFragment) {
            super(destinationFragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            Fragment fragment = new Fragment();

            switch (position) {
                case 0:
                    fragment = startOnBoardingFragment();
                    break;
                case 1:
                    fragment = startMatchFragment();
                    break;
                case 2:
                    fragment = startRoommateFragment();
                    break;
                default:
                    break;
            }
            return fragment;
        }

        @Override
        public int getItemCount() {
            return References.NUM_PAGES_MAIN;
        }

        private Fragment startOnBoardingFragment() {
            OnBoardingFragment fragment = (OnBoardingFragment) getParentFragmentManager().findFragmentByTag(References.ON_BOARDING_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new OnBoardingFragment();
            }
        }

        private Fragment startMatchFragment() {
            MatchFragment fragment = (MatchFragment) getParentFragmentManager().findFragmentByTag(References.MATCH_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new MatchFragment();
            }
        }

        private Fragment startRoommateFragment() {
            RoommateFragment fragment = (RoommateFragment) getParentFragmentManager().findFragmentByTag(References.ROOMMATE_FRAGMENT);

            if (fragment != null) {
                return fragment;
            } else {
                return new RoommateFragment();
            }
        }
    }
}