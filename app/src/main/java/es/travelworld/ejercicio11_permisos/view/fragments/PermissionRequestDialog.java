package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.travelworld.ejercicio11_permisos.R;

import java.util.Objects;

public class PermissionRequestDialog extends DialogFragment implements View.OnClickListener {

    public PermissionRequestDialog() {
        // Empty constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.permission_request_dialog,container);



        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getDialog()).getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_LOCAL_FOCUS_MODE);

    }


    public static PermissionRequestDialog newInstance() {
        return new PermissionRequestDialog();
    }

    @Override
    public void onClick(View view) {

        dismiss();
    }
}
