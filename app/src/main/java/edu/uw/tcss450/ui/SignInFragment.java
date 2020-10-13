package edu.uw.tcss450.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.ui.SignInFragmentDirections;
import edu.uw.tcss450.databinding.FragmentSignInBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment implements View.OnClickListener{

    private FragmentSignInBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void processSignIn(String email) {
        SignInFragmentDirections.ActionSignInFragmentToSuccessFragment directions =
                SignInFragmentDirections.actionSignInFragmentToSuccessFragment(email);

        Navigation.findNavController(getView()).navigate(directions);
    }

    public void processRegister() {
        Navigation.findNavController(getView()).navigate(SignInFragmentDirections.actionSignInFragmentToRegisterFragment());
    }


    @Override
    public void onClick(View v) {
        if (v == binding.signin) {
            if (checkSignIn(binding.email.getText().toString(), binding.password.getText().toString())) {
                processSignIn(binding.email.getText().toString());
            }
        }
        if (v == binding.register) {
            processRegister();
        }
    }

    public boolean checkSignIn(String email, String password) {
        //display error for both password and email if that's the case
        boolean status = true;
        if (!email.contains("@")) {
            binding.email.setError("Email does not contain @");
            status = false;
        }
        if (email.isEmpty()) {
            binding.email.setError("Email is empty");
            status = false;
        }
        if (password.isEmpty()) {
            binding.password.setError("Password is empty");
            status = false;
        }
        return status;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.signin.setOnClickListener(this);
        binding.register.setOnClickListener(this);
    }


}