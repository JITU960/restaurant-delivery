package com.wync.rastaurant.services;

import com.wync.rastaurant.request.GetOrderStatusRequest;
import com.wync.rastaurant.request.PlaceOrderRequest;
import com.wync.rastaurant.request.UpdateOrderStatusRequest;
import com.wync.rastaurant.response.GetOrderStatusResponse;
import com.wync.rastaurant.response.PlaceOrderResponse;
import com.wync.rastaurant.response.UpdateOrderStatusResponse;

public interface IOrderManagementService {
    public GetOrderStatusResponse getOrderStatus(GetOrderStatusRequest request);

    public PlaceOrderResponse placeOrder(PlaceOrderRequest request);

    public UpdateOrderStatusResponse updateOrderStatus(UpdateOrderStatusRequest request);
}
