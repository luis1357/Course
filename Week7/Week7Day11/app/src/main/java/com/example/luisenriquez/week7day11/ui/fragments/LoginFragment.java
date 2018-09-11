package com.example.luisenriquez.week7day11.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisenriquez.week7day11.ActivityCallback;
import com.example.luisenriquez.week7day11.R;
import com.example.luisenriquez.week7day11.utils.Constants;
import com.example.luisenriquez.week7day11.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginFragment extends Fragment
{
    private AutoCompleteTextView etEmail;
    private EditText etPassword;
    private View mProgressView;
    private View mLoginFormView;

    private ActivityCallback mCallback;

    private FirebaseAuth mAuth;

    public static LoginFragment newInstance()
    {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_login, container, false);

        etEmail = root.findViewById(R.id.username);
        etPassword = root.findViewById(R.id.password);

        final Button signInButton =  root.findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyboard(Objects.requireNonNull(getContext()), signInButton);
                attemptLogin();
            }
        });

        final Button createAccount = (Button) root.findViewById(R.id.create_account_button);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyboard(Objects.requireNonNull(getContext()), createAccount);
                mCallback.openCreateAccount();
            }
        });

        mLoginFormView = root.findViewById(R.id.login_form);
        mProgressView = root.findViewById(R.id.login_progress);

        mAuth = FirebaseAuth.getInstance();
        Utils.closeKeyboard(Objects.requireNonNull(getContext()), etEmail);

        return root;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        mCallback = (ActivityCallback) context;
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mCallback = null;
    }

    private void attemptLogin() {

        /* Reset errors. */
        etEmail.setError(null);
        etPassword.setError(null);

        /* Store values at the time of the login attempt. */
        String username = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        /* Check for a valid email address. */
        if (TextUtils.isEmpty(username))
        {
            etEmail.setError("Please Fill your Username!");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password))
        {
            etPassword.setError("Please fill your password!");
            etPassword.requestFocus();
            return;
        }

        login();
    }

    private void login()
    {
        showProgress(true);

        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        mAuth.signInWithEmailAndPassword(email,
                password).addOnSuccessListener(new OnSuccessListener<AuthResult>()
        {
            @Override
            public void onSuccess(AuthResult authResult)
            {
                if (mCallback != null)
                {
                    Utils.saveLocalUser(Objects.requireNonNull(getContext()),
                                        Constants.DEFAULT_USER,
                            etEmail.getText().toString(),
                            authResult.getUser().getUid());

                    mCallback.openChat();
                }
            }
        }).addOnFailureListener(new OnFailureListener()
        {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                showProgress(false);
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showProgress(boolean show)
    {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
