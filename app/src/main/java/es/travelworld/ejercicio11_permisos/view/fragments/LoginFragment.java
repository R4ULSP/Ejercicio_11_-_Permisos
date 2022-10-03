package es.travelworld.ejercicio11_permisos.view.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import com.travelworld.ejercicio11_permisos.R;
import com.travelworld.ejercicio11_permisos.databinding.FragmentLoginBinding;

import es.travelworld.ejercicio11_permisos.domain.User;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private FragmentLoginBinding binding;
    private User user;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = LoginFragmentArgs.fromBundle(getArguments()).getArgUser();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        user = new User("1234","5678","18-99","1234");

        if(user != null){
            Snackbar.make(binding.getRoot(), "Nombre: " + user.getName() + "  Apellidos: " + user.getLastname() + "  Edad:" + user.getAgeGroup(), BaseTransientBottomBar.LENGTH_LONG).show();
        }

        setListeners();
        return view;
    }

    private void setListeners() {
        binding.loginForgotPasswordButton.setOnClickListener(this);
        binding.loginNewAccountButton.setOnClickListener(this);
        binding.loginButton.setOnClickListener(this);

        binding.loginInputUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });

        binding.loginInputPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
    }

    private void validateForm() {
        binding.loginButton.setEnabled(false);
        boolean userValidation = false;
        boolean passwordValidation = false;

        if (binding.loginInputUser.getText() != null && !binding.loginInputUser.getText().toString().equals("")) {
            userValidation = true;
        }
        if (binding.loginInputPassword.getText() != null && !binding.loginInputPassword.getText().toString().equals("")) {
            passwordValidation = true;
        }

        if (userValidation && passwordValidation) {
            binding.loginButton.setEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {
        if (binding.loginForgotPasswordButton.equals(view)) {
            Snackbar.make(binding.getRoot(), R.string.wip_feature, BaseTransientBottomBar.LENGTH_LONG).show();
        } else if (binding.loginNewAccountButton.equals(view)) {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.to_registerFragment);
        } else if (binding.loginButton.equals(view)) {
            login();
        }
    }

    private void login() {
        if (user != null && Objects.requireNonNull(binding.loginInputPassword.getText()).toString().equals(user.getPassword()) && Objects.requireNonNull(binding.loginInputUser.getText()).toString().equals(user.getName())) {
            LoginFragmentDirections.ToHomeActivity directions = LoginFragmentDirections.toHomeActivity().setLoginUser(user);
            Navigation.findNavController(requireView()).navigate(directions);
        } else {
            FragmentManager fragmentManager = getParentFragmentManager();
            LoginErrorFragment loginErrorFragment = LoginErrorFragment.newInstance();
            loginErrorFragment.show(fragmentManager, null);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}