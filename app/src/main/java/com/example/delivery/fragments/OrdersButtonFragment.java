package com.example.delivery.fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.delivery.R;
import com.example.delivery.activities.OrdersButtonsActivity;
import com.example.delivery.services.ReceiverService;

import java.util.Calendar;


public class OrdersButtonFragment extends Fragment {

    public OrdersButtonFragment() {

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_buttons, container, false);

        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                calendar.setTimeInMillis(System.currentTimeMillis());

                int minute;
                int hour;
                int day;
                int month;
                int year;

                try {

                    String time[] = ((OrdersButtonsActivity) getActivity()).getTime().split(":");
                    String date[] = ((OrdersButtonsActivity) getActivity()).getDate().split("/");

                    hour = Integer.parseInt(time[0]);
                    minute = Integer.parseInt(time[1]);

                    day = Integer.parseInt(date[0]);
                    month = Integer.parseInt(date[1]);
                    year = Integer.parseInt(date[2]);
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Error trying to set the alarm!", Toast.LENGTH_SHORT).show();
                    return;
                }
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month-1);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                Intent notifyIntent = new Intent(getContext(), ReceiverService.class);
                notifyIntent.putExtra("address", getActivity().getIntent().getExtras().getString("address"));
                PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(getContext(), 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), notifyPendingIntent);
                Toast.makeText(getContext(), "Alarm set!", Toast.LENGTH_LONG).show();

                getActivity().onBackPressed();

            }
        });

        return view;
    }
}
