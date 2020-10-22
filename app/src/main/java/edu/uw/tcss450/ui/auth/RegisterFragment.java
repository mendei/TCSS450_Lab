package edu.uw.tcss450.ui.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import edu.uw.tcss450.ui.auth.RegisterFragmentDirections;
import edu.uw.tcss450.databinding.FragmentRegisterBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener{

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void processRegister(String email, String jwt) {
        RegisterFragmentDirections.ActionRegisterFragmentToMainActivity directions =
                RegisterFragmentDirections.actionRegisterFragmentToMainActivity(email, jwt);

        Navigation.findNavController(getView()).navigate(directions);
        getActivity().finish();
    }


    public boolean checkRegister(String fname, String lname, String email,
                               String password, String repassword) {
        //display error for both password and email if that's the case
        boolean status = true;
        if (fname.isEmpty()) {
            binding.firstname.setError("First Name is empty");
            status = false;
        }
        if (lname.isEmpty()) {
            binding.lastname.setError("Last Name is empty");
            status = false;
        }
        if (!email.contains("@")) {
            binding.email.setError("Email does not contain @");
            status = false;
        }
        if (email.isEmpty()) {
            binding.email.setError("Email is empty");
            status = false;
        }
        if (password.length() < 6) {
            binding.registerPassword.setError("Password is less than 6 characters");
            status = false;
        }
        if (password.isEmpty()) {
            binding.registerPassword.setError("Password is empty");
            status = false;
        }
        if (!repassword.contentEquals(password)) {
            binding.retypePassword.setError("Password does not match");
            status = false;
        }
        return status;
    }


    @Override
    public void onClick(View v) {
        if (v == binding.register) {
            if (checkRegister(binding.firstname.getText().toString(),
                    binding.lastname.getText().toString(),
                    binding.email.getText().toString(),
                    binding.registerPassword.getText().toString(),
                    binding.retypePassword.getText().toString())) {
                processRegister(binding.email.getText().toString(), "");
            }

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.register.setOnClickListener(this);
    }
}