package com.wync.rastaurant.services.serviceImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.wync.rastaurant.enums.OrderStatus;
import com.wync.rastaurant.entities.Order;
import com.wync.rastaurant.request.GetOrderStatusRequest;
import com.wync.rastaurant.request.PlaceOrderRequest;
import com.wync.rastaurant.request.UpdateOrderStatusRequest;
import com.wync.rastaurant.response.GetOrderStatusResponse;
import com.wync.rastaurant.response.PlaceOrderResponse;
import com.wync.rastaurant.response.UpdateOrderStatusResponse;
import com.wync.rastaurant.services.IOrderManagementService;

@Service
public class OrderManagementServiceImpl implements IOrderManagementService {

    private static final ConcurrentHashMap<Integer, Order> orders = new ConcurrentHashMap<>();

    private static Integer maxOrderId = new Integer(0);

    private Calendar calendar = Calendar.getInstance();

    @Override
    public GetOrderStatusResponse getOrderStatus(GetOrderStatusRequest request) {
        Order order = orders.get(request.getOrderId());
        GetOrderStatusResponse response = new GetOrderStatusResponse();
        if(order != null) {
            response.setOrderId(order.getOrderId());
            response.setOrderStatus(order.getStatus());
        }
        return response;
    }

    @Override
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        Order newOrder = new Order();

        newOrder.setOrderId(getOrderId());

        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        newOrder.setExpectedDeliveryAt(calendar.getTime());

        newOrder.setStatus(OrderStatus.CONFIRMED);

        newOrder.setDeliveryAddress(request.getDeliveryAddress());

        orders.put(newOrder.getOrderId(), newOrder);
        //Call Delivery Person Api to Assign this order to available person
        return new PlaceOrderResponse(newOrder.getOrderId(), newOrder.getStatus());
    }

    @Override
    public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest request) {
        orders.get(request.getOrderId()).setStatus(request.getOrderStatus());
        return new UpdateOrderStatusResponse(request.getOrderStatus());
    }

    private int getOrderId() {
        synchronized (maxOrderId) {
            maxOrderId++;
            return maxOrderId;
        }
    }
}
