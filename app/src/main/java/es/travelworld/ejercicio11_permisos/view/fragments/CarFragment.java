package es.travelworld.ejercicio11_permisos.view.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.travelworld.ejercicio11_permisos.databinding.FragmentCarBinding;

import java.util.ArrayList;
import java.util.List;

import es.travelworld.ejercicio11_permisos.domain.CarItem;
import es.travelworld.ejercicio11_permisos.view.fragments.adapter.CarListAdapter;


public class CarFragment extends Fragment {

    public CarFragment() {
        // Required empty public constructor
    }

    public static CarFragment newInstance() {
        return new CarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentCarBinding binding = FragmentCarBinding.inflate(inflater, container, false);

        setUpRecycler(binding);

        return binding.getRoot();
    }

    private void setUpRecycler(FragmentCarBinding binding) {
        binding.carList.setHasFixedSize(true);
        binding.carList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.carList.setAdapter(new CarListAdapter(getCarList(), carItem -> Toast.makeText(requireActivity(),carItem.getCarName(),Toast.LENGTH_SHORT).show()));
    }

    private List<CarItem> getCarList() {
        List<CarItem> list = new ArrayList<>();
        list.add(new CarItem(34f, "classic", "Classic Car"));
        list.add(new CarItem(55f, "sport", "Sport Car"));
        list.add(new CarItem(500f, "flying", "Flying Car"));
        list.add(new CarItem(45f, "electric", "Electric Car"));
        list.add(new CarItem(23f, "motor_home", "Motorhome"));
        list.add(new CarItem(10f, "pick_up", "Pickup"));
        list.add(new CarItem(11f, "airplane", "Airplane"));
        list.add(new CarItem(14f, "bus", "Bus"));

        return list;
    }
}