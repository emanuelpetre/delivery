package com.example.delivery.services;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthService {
    private FirebaseAuth mAuth;

    public AuthService() {
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser(){
        return mAuth.getCurrentUser();
    }
    public Task<AuthResult> signInUser(String email, String password){
       return mAuth.signInWithEmailAndPassword(email,password);
    }
    public Task<AuthResult> registerUser(String email, String password){
       return mAuth.createUserWithEmailAndPassword(email,password);
    }

    public void logout() {
        mAuth.signOut();
    }
}
