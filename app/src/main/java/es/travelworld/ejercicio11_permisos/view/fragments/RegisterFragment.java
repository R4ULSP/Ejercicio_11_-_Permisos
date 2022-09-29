package es.travelworld.ejercicio11_permisos.view.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

import com.travelworld.ejercicio11_permisos.R;
import com.travelworld.ejercicio11_permisos.databinding.FragmentRegisterBinding;

import es.travelworld.ejercicio11_permisos.domain.User;


public class RegisterFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {


    private FragmentRegisterBinding binding;
    private String[] ages;

    public RegisterFragment() {
        // Required empty public constructor
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ages = getArguments().getStringArray("ages");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        populateAgeEditText();
        setListeners();

        return view;
    }

    private void setListeners() {
        binding.registerAvatarImgSelector.setOnClickListener(this);
        binding.registerViewConditionsButton.setOnClickListener(this);
        binding.registerJoinButton.setOnClickListener(this);
        binding.registerInputAge.setOnItemClickListener(this);

        binding.registerInputName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.registerNameLayout.setErrorEnabled(false);
                validateChars(charSequence, binding.registerNameLayout);
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
        binding.registerInputLastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not implemented yet
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                binding.registerLastnameLayout.setErrorEnabled(false);
                validateChars(charSequence, binding.registerLastnameLayout);
                validateForm();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Not implemented yet
            }
        });
    }

    private void validateChars(CharSequence charSequence, TextInputLayout textInputLayout) {
        for (int j = 0; j < charSequence.length(); j++) {
            switch (charSequence.charAt(j)) {
                case '!':
                case '@':
                    textInputLayout.setError(getString(R.string.input_layput_name_error));
                    break;
                default:
                    break;
            }
        }
    }

    private void validateForm() {
        binding.registerJoinButton.setEnabled(false);
        boolean nameValidation = false;
        boolean lastnameValidation = false;
        boolean ageValidation = false;

        if (binding.registerInputName.getText() != null && !binding.registerInputName.getText().toString().equals("") && !binding.registerNameLayout.isErrorEnabled()) {
            nameValidation = true;
        }
        if (binding.registerInputLastname.getText() != null && !binding.registerInputLastname.getText().toString().equals("") && !binding.registerLastnameLayout.isErrorEnabled()) {
            lastnameValidation = true;
        }
        if (binding.registerInputAge.getText() != null && !binding.registerInputAge.getText().toString().equals("") && !binding.registerAgeLayout.isErrorEnabled()) {
            ageValidation = true;
        }

        if (nameValidation && lastnameValidation && ageValidation) {
            binding.registerJoinButton.setEnabled(true);
        }
    }

    private void populateAgeEditText() {
        ages = getResources().getStringArray(R.array.ages);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, ages);
        binding.registerInputAge.setAdapter(adapter);
    }

    @Override

    public void onClick(View view) {
        Intent intent;
        if (view.equals(binding.registerAvatarImgSelector)) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(intent);
        } else if (view.equals(binding.registerViewConditionsButton)) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/ml-kit/terms"));
            startActivity(intent);
        } else if (view.equals(binding.registerJoinButton)) {
            User user = new User();
            user.setPassword("1234");
            user.setName(Objects.requireNonNull(binding.registerInputName.getText()).toString());
            user.setLastname(Objects.requireNonNull(binding.registerInputLastname.getText()).toString());
            user.setAgeGroup(binding.registerInputAge.getText().toString());

            RegisterFragmentDirections.ToLoginFragmentFromRegisterFragment action = RegisterFragmentDirections.toLoginFragmentFromRegisterFragment().setArgUser(user);
            NavHostFragment.findNavController(RegisterFragment.this).navigate(action);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MaterialTextView materialTextView = (MaterialTextView) view;
        binding.registerAgeLayout.setErrorEnabled(false);
        if (materialTextView.getText() != ages[ages.length - 1]) {
            binding.registerAgeLayout.setError(getString(R.string.input_layout_age_error));
        }
        validateForm();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}