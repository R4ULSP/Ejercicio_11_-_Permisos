package es.travelworld.ejercicio11_permisos.view;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.travelworld.ejercicio11_permisos.R;
import com.travelworld.ejercicio11_permisos.databinding.ActivityLoginBinding;

import java.util.Objects;

import es.travelworld.ejercicio11_permisos.view.fragments.MatchFragment;
import es.travelworld.ejercicio11_permisos.view.fragments.RoommateFragment;

public class LoginActivity extends AppCompatActivity implements MatchFragment.OnClickItemMatchFragment, RoommateFragment.OnClickItemRoommateFragment {

    private ActivityLoginBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpNavigation();
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