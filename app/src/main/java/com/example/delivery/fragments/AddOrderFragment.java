package com.example.delivery.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.delivery.R;
import com.example.delivery.models.Order;
import com.example.delivery.repositories.OrdersRepository;

import java.util.Calendar;


public class AddOrderFragment extends Fragment {


    OrdersRepository ordersRepository;
    public AddOrderFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ordersRepository = new OrdersRepository(getContext());

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_order, container, false);

        Button timeButton = view.findViewById(R.id.timeButton);
        Button dateButton = view.findViewById(R.id.dateButton);

        Button addOrderButton = view.findViewById(R.id.addOrderButton);

        addOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order order = new Order();

                TextView name = view.findViewById(R.id.nameText);
                TextView phoneNumber = view.findViewById(R.id.phoneNumberText);
                TextView address = view.findViewById(R.id.addressText);
                TextView date = view.findViewById(R.id.dateView);
                TextView time = view.findViewById(R.id.pickTimeView);

                order.setName(name.getText().toString());
                order.setPhoneNumber(phoneNumber.getText().toString());
                order.setAddress(address.getText().toString());
                order.setDate(date.getText().toString().split(" : ")[1]);
                order.setTime(time.getText().toString().split(" : ")[1]);

                ordersRepository.insert(order);
                getActivity().onBackPressed();
            }
        });

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);
                final TextView dateView = view.findViewById(R.id.dateView);

                DatePickerDialog datePicker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                                String selectedDay, selectedMonth;
                                monthOfYear = monthOfYear + 1;
                                if (monthOfYear < 10)
                                    selectedMonth = "0" + monthOfYear;
                                else
                                    selectedMonth = Integer.toString(monthOfYear);
                                if (dayOfMonth < 10)
                                    selectedDay = "0" + dayOfMonth;
                                else
                                    selectedDay = Integer.toString(dayOfMonth);
                                dateView.setText(dateView.getText() + " : " +selectedDay + "/" + selectedMonth + "/" + year);

                            }
                        }, year, month, day);
                datePicker.show();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minutes = calendar.get(Calendar.MINUTE);
                final TextView timeView = view.findViewById(R.id.pickTimeView);

                TimePickerDialog timePicker = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                                String hour = "" + sHour;
                                String minute = "";
                                if (sMinute < 10)
                                    minute = "0";
                                minute = minute + sMinute;
                                timeView.setText(timeView.getText() + " : " +hour + ":" + minute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
        return view;
    }



}
