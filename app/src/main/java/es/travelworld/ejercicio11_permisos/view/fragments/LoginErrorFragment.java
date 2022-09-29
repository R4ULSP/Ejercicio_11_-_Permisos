package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;

import java.util.Objects;

import com.travelworld.ejercicio11_permisos.R;

public class LoginErrorFragment extends DialogFragment implements View.OnClickListener {


    public LoginErrorFragment() {
        // Empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.fragment_login_error, container);

        MaterialButton buttonUnderstood = view.findViewById(R.id.button_understood);
        buttonUnderstood.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);

    }


    public static LoginErrorFragment newInstance() {
        return new LoginErrorFragment();
    }

    @Override
    public void onClick(View view) {
        dismiss();
    }
}