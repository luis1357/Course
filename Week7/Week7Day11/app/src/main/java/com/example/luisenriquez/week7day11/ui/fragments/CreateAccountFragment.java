package com.example.luisenriquez.week7day11.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luisenriquez.week7day11.ActivityCallback;
import com.example.luisenriquez.week7day11.R;
import com.example.luisenriquez.week7day11.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class CreateAccountFragment extends Fragment
{
    /** UI Components **/
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private View mProgressView;
    private View mCreateForm;

    /** Activity callback **/
    private ActivityCallback mCallback;

    /** Firebase objects **/
    private FirebaseAuth mAuth;

    /**
     * Create a instance of this fragment
     *
     * @return fragment instance
     */
    public static CreateAccountFragment newInstance()
    {
        return new CreateAccountFragment();
    }

    /** LifeCycle Methods. */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_create, container, false);

        mUsername = root.findViewById(R.id.create_account_username);
        mPassword = root.findViewById(R.id.create_account_password);
        mEmail = root.findViewById(R.id.create_account_email);

        mCreateForm = root.findViewById(R.id.create_account_form);
        mProgressView = root.findViewById(R.id.create_account_progress);

        Button createButton = root.findViewById(R.id.create_account_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        mAuth = FirebaseAuth.getInstance();

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

    /** Private Methods. */

    private void createAccount()
    {
        showProgress(true);

        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(getActivity()),
                        new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task)
                            {
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(getContext(),
                                            "Failed to Create Account!",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Utils.saveLocalUser(Objects.requireNonNull(getContext()),
                                            mUsername.getText().toString(),
                                            mEmail.getText().toString(),
                                            task.getResult().getUser().getUid());

                                    mCallback.openChat();
                                }

                                showProgress(false);
                                Utils.closeKeyboard(getContext(), mEmail);
                            }
                        });
    }

    private void showProgress(boolean show)
    {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mCreateForm.setVisibility(show ? View.GONE : View.VISIBLE);
    }
}
