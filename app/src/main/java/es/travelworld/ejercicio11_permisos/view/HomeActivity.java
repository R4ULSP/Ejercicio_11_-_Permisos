package es.travelworld.ejercicio11_permisos.view;


import static es.travelworld.ejercicio11_permisos.domain.References.KEY_GEO_PERMISSION;
import static es.travelworld.ejercicio11_permisos.domain.References.PRUEBAS;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
import com.travelworld.ejercicio11_permisos.databinding.ActivityHomeBinding;

import java.util.Objects;

import es.travelworld.ejercicio11_permisos.view.fragments.WipFragment;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private NavController navController;
    private boolean permissionGranted;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupNavigation(getIntent().getExtras());

        setUpSharedPreferences();
        checkPermissions();
    }

    private void setUpSharedPreferences() {
        SharedPreferences preferences = getSharedPreferences("geoPermission", Context.MODE_PRIVATE);
        permissionGranted = preferences.getBoolean(KEY_GEO_PERMISSION, false);
        editor = preferences.edit();
    }

    private void checkPermissions() {
        Log.d(PRUEBAS, "granted: " + permissionGranted + " rationale: " + showRationale());

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //Permisos no concedidos
            askForPermission();
        }
    }

    private void askForPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1234);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 12345);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        int result = 0;
        for (int grantResult : grantResults) {
            result = grantResult;
        }

        if (result == -1) {
            editor.putBoolean(KEY_GEO_PERMISSION, false);
            showPermissionsDialog(getString(R.string.location_permission), getString(R.string.permissions_denied));
        } else {
            editor.putBoolean(KEY_GEO_PERMISSION, true);
            return;
        }

        if (!permissionGranted && !showRationale()) {
            showRejectedDialog(getString(R.string.location_permission), getString(R.string.permissions_rejected));
        }
    }

    private boolean showRationale() {
        return shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private void showPermissionsDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes, (dialogInterface, i) ->
                        //Click salir
                        finish()
                ).setNegativeButton(R.string.no, (dialogInterface, i) ->
                        //Click pedir permiso de nuevo
                        checkPermissions()
                ).setOnCancelListener(dialogInterface ->
                        finish()
                );

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showRejectedDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
                    //Click salir
                    finish();
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupNavigation(Bundle bundle) {
        setSupportActionBar(binding.toolbar);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.mainFragmentFrame.getId());
        navController = Objects.requireNonNull(navHostFragment).getNavController();

        navController.setGraph(R.navigation.nav_graph_main, bundle);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.eurodisney) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.disneylandparis.com/es-es/"));
            startActivity(intent);
        }

        if (item.getItemId() == R.id.rentacar) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            WipFragment wipFragment = WipFragment.newInstance();
            wipFragment.show(fragmentManager, null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        int currentFragment = Objects.requireNonNull(navController.getCurrentDestination()).getId();

        if (currentFragment == R.id.mainFragment) {
            navController.navigate(R.id.to_loginActivity_from_mainFragment);
            finish();
        }
    }


}