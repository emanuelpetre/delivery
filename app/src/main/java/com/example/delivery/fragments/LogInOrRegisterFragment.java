package com.example.delivery.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.delivery.R;
import com.example.delivery.activities.LogInOrRegisterActivity;
import com.example.delivery.activities.MainActivity;
import com.example.delivery.services.AuthService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LogInOrRegisterFragment extends Fragment {

    AuthService auth;
    public LogInOrRegisterFragment() {
        auth = new AuthService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.fragment_log_in_or_register, container, false);
        Button loginButton = view.findViewById(R.id.signInButton);
        Button registerButton = view.findViewById(R.id.RegisterButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TextView email = view.findViewById(R.id.emailText);
               TextView password = view.findViewById(R.id.passwordText);

               auth.signInUser(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Intent intent = new Intent(getContext(), MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   }
               });
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               TextView email = view.findViewById(R.id.emailText);
               TextView password = view.findViewById(R.id.passwordText);

               auth.registerUser(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       Intent intent = new Intent(getContext(), MainActivity.class);
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                   }
               });
            }
        });

        return view;
    }
}
