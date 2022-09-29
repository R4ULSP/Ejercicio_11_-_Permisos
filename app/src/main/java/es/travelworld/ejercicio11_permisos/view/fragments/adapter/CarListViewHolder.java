package es.travelworld.ejercicio11_permisos.view.fragments.adapter;


import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.travelworld.ejercicio11_permisos.R;
import com.travelworld.ejercicio11_permisos.databinding.ItemlayoutCarListBinding;

import es.travelworld.ejercicio11_permisos.domain.CarItem;

public class CarListViewHolder extends RecyclerView.ViewHolder {

    private final ItemlayoutCarListBinding binding;
    private final CarListAdapter.OnItemCarClick listener;

    public CarListViewHolder(@NonNull ItemlayoutCarListBinding bindingView, CarListAdapter.OnItemCarClick listener) {
        super(bindingView.getRoot());
        this.binding = bindingView;
        this.listener = listener;

    }

    public void bind(CarItem carItem) {
        binding.carName.setText(carItem.getCarName());
        binding.carPhoto.setImageResource(getImageFromTypeCar(carItem.getCarType()));
        binding.carPrice.setText(itemView.getContext().getString(R.string.price_car, carItem.getCarPrice()));
        binding.cardItem.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), (getCardColor(carItem.getCarType()))));

        itemView.setOnClickListener(view -> listener.onItemClick(carItem));
    }

    private int getCardColor(String carType) {
        switch (carType) {
            case "sport":
                return R.color.sport_car;
            case "flying":
                return R.color.flying_car;
            case "electric":
                return R.color.electric_car;
            case "motor_home":
                return R.color.motor_home;
            case "pick_up":
                return R.color.pick_up;
            case "airplane":
                return R.color.airplane;
            case "bus":
                return R.color.bus;
            default:
                return R.color.classic_car;
        }
    }

    private int getImageFromTypeCar(String carType) {
        switch (carType) {
            case "sport":
                return R.drawable.sport_cart;
            case "flying":
                return R.drawable.flying_car;
            case "electric":
                return R.drawable.electric_car;
            case "motor_home":
                return R.drawable.motor_home;
            case "pick_up":
                return R.drawable.pick_up_car;
            case "airplane":
                return R.drawable.air_plain;
            case "bus":
                return R.drawable.bus;
            default:
                return R.drawable.classic_car;
        }
    }
}
