package es.travelworld.ejercicio11_permisos.view;


import static es.travelworld.ejercicio11_permisos.domain.References.LOCATION_REQUEST_CODE;
import static es.travelworld.ejercicio11_permisos.domain.References.PRUEBAS;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.travelworld.ejercicio11_permisos.R;
import com.travelworld.ejercicio11_permisos.databinding.ActivityLoginBinding;

import java.util.Objects;

import es.travelworld.ejercicio11_permisos.view.fragments.LoginErrorFragment;
import es.travelworld.ejercicio11_permisos.view.fragments.MatchFragment;
import es.travelworld.ejercicio11_permisos.view.fragments.PermissionRequestDialog;
import es.travelworld.ejercicio11_permisos.view.fragments.RoommateFragment;

public class LoginActivity extends AppCompatActivity implements MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment {

    private ActivityLoginBinding binding;
    private NavController navController;
    private boolean permissionAsked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNavigation();

        checkPermissions();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Permisos no concedidos

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                showPermissionsDialog(getString(R.string.location_permission), getString(R.string.permissions_denied), LOCATION_REQUEST_CODE);
            } else {
                askForPermission();
                checkPermissions();
            }

            //TODO Si se deniega para siempre no hay forma de controlar, hay que evitar cualquier loop y que el usuario pueda acceder sin aceptar el permiso
        }


    }

    private void showPermissionsDialog(String title, String message, int locationRequestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    //Click salir
                    finish();
                }).setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    //Click pedir permiso de nuevo
                    askForPermission();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

    }

    private void setUpNavigation() {
        setSupportActionBar(binding.materialToolbar); //Establecer la action bar
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.loginFragmentFrame.getId());
        navController = Objects.requireNonNull(navHostFragment).getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build(); //Ceder la parte de la actionBar a la appBar
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public void matchSkipButton() {
        navigateToLogin();
    }

    private void navigateToLogin() {
        navController.navigate(R.id.to_loginFragment_from_destinationFragment);
    }

    @Override
    public void roommateLoginButton() {
        navigateToLogin();
    }

    @Override
    public void onBackPressed() {
        int currentFragment = Objects.requireNonNull(navController.getCurrentDestination()).getId();

        if (currentFragment == R.id.loginFragment) {
            navController.navigate(R.id.to_destinationFragment_from_loginFragment);
        } else if (currentFragment == R.id.registerFragment) {
            navController.navigate(R.id.to_loginFragment_from_registerFragment);
        } else if (currentFragment == R.id.destinationFragment) {
            finish();
        }
    }


}