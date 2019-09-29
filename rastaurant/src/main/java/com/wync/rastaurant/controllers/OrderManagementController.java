package com.wync.rastaurant.controllers;

import com.wync.rastaurant.request.GetOrderStatusRequest;
import com.wync.rastaurant.request.PlaceOrderRequest;
import com.wync.rastaurant.request.UpdateOrderStatusRequest;
import com.wync.rastaurant.response.GetOrderStatusResponse;
import com.wync.rastaurant.response.PlaceOrderResponse;
import com.wync.rastaurant.response.UpdateOrderStatusResponse;
import com.wync.rastaurant.services.IOrderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderManagementController extends AbstractController {

    @Autowired
    IOrderManagementService oms;

    @RequestMapping(value = "/create", produces = "application/json", method = RequestMethod.POST)
    public PlaceOrderResponse placeOrder(PlaceOrderRequest request) {
        return oms.placeOrder(request);
    }

    @RequestMapping(value = "/status", produces = "application/json", method = RequestMethod.GET)
    public GetOrderStatusResponse getStatus(GetOrderStatusRequest request) {
        return oms.getOrderStatus(request);
    }

    @RequestMapping(value = "/update", produces = "application/json", method = RequestMethod.POST)
    public UpdateOrderStatusResponse updateStatus(UpdateOrderStatusRequest request) {
        return oms.updateOrderStatus(request);
    }
}
