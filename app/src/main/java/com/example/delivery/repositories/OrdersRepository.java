package com.example.delivery.repositories;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.delivery.models.Order;
import com.example.delivery.models.OrderDAO;
import com.example.delivery.services.DatabaseService;

import java.util.List;

public class OrdersRepository {


    public OrderDAO orderDao;

    public OrdersRepository(Context context) {
        DatabaseService db = DatabaseService.getOrderDatabase(context);
        orderDao = db.orderDAO();
    }

    public  void insert (Order order)
    {
        new InsertOrderAsyncTask(orderDao).execute(order);
    }
    private static class InsertOrderAsyncTask extends AsyncTask<Order,Void,Void> {
        private OrderDAO orderDao;

        private InsertOrderAsyncTask(OrderDAO orderDAO)
        {
            this.orderDao = orderDAO;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insert(orders[0]);
            return null;
        }
    }


    public List<Order> getAllOrders(){
        return orderDao.getAll();
    }
}
