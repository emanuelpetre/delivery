package com.example.delivery.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delivery.R;
import com.example.delivery.activities.OrdersButtonsActivity;
import com.example.delivery.models.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;
    private List<Order> orders;

    public OrderAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, final int position) {
        Order order = null;
        if (orders != null) {
            order = orders.get(position);
            holder.setData(order, position);
        }

        final Order finalOrder = order;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Intent intent = new Intent(activity, OrdersButtonsActivity.class);
                intent.putExtra("date", finalOrder.getDate());
                intent.putExtra("time", finalOrder.getTime());
                intent.putExtra("address", finalOrder.getAddress());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (orders != null) {
            return orders.size();
        }
        return 0;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView orderItemView;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderItemView = itemView.findViewById(R.id.order_view);
        }
//
        public void setData(Order order, int position) {
            orderItemView.setText(order.getName() + " " + order.getPhoneNumber() +"\n" + order.getAddress() + "\n" + order.getDate() + " - " + order.getTime());
            this.position = position;
        }
    }
}
