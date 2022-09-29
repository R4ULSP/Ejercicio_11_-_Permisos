package es.travelworld.ejercicio11_permisos.view.fragments.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.travelworld.ejercicio11_permisos.databinding.ItemlayoutCarListBinding;

import java.util.List;

import es.travelworld.ejercicio11_permisos.domain.CarItem;

public class CarListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CarItem> items;
    private final OnItemCarClick listener;



    public CarListAdapter(List<CarItem> items, OnItemCarClick listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemlayoutCarListBinding binding = ItemlayoutCarListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CarListViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CarListViewHolder){
            ((CarListViewHolder)holder).bind(items.get(position));
        }

    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public interface OnItemCarClick{
        void onItemClick(CarItem carItem);
    }
}
