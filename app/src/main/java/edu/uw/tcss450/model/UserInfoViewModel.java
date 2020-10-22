package edu.uw.tcss450.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class UserInfoViewModel extends ViewModel {
    public String getEmail() {
        return mEmail;
    }

    private UserInfoViewModel(String email, String jwt) {
        mEmail = email;
        mJwt = jwt;
    }

    private String mEmail;

    public String getJwt() {
        return mJwt;
    }

    private String mJwt;

    public static class UserInfoViewModelFactory implements ViewModelProvider.Factory {
        private final String email;

        private final String jwt;

        public UserInfoViewModelFactory(String email, String jwt) {
            this.email = email;
            this.jwt = jwt;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass == UserInfoViewModel.class) {
                return (T) new UserInfoViewModel(email, jwt);
            }
            throw new IllegalArgumentException("Argument must be: " + UserInfoViewModel.class);
        }
    }
}
