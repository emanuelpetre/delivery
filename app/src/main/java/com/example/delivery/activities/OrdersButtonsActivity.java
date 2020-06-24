package com.example.delivery.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.delivery.R;

public class OrdersButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders_buttons);
    }

    public String getDate(){
        return getIntent().getExtras().getString("date");
    }

    public String getTime(){
        return getIntent().getExtras().getString("time");
    }
}
