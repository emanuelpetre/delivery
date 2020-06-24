package com.example.delivery.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.delivery.R;
import com.example.delivery.adapters.OrderAdapter;
import com.example.delivery.repositories.OrdersRepository;

import java.util.List;


public class SeeAllOrdersFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_see_all_orders, container, false);

        final OrdersRepository ordersRepository = new OrdersRepository(getContext());

        RecyclerView recyclerView = view.findViewById(R.id.recycler);
        final OrderAdapter orderAdapter = new OrderAdapter(getContext());
        recyclerView.setAdapter(orderAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        orderAdapter.setOrders(ordersRepository.getAllOrders());


        return view;
    }
}
