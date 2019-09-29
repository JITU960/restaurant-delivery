package com.wync.delivery.controllers;

import com.wync.delivery.request.AssignOrderRequest;
import com.wync.delivery.request.GetDeliveryPersonStatusRequest;
import com.wync.delivery.response.AssignOrderResponse;
import com.wync.delivery.response.GetDeliveryPersonStatusResponse;
import com.wync.delivery.service.IDeliveryPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/delivery")
public class DeliveryPersonManagementController {

    @Autowired
    IDeliveryPersonService deliveryPersonService;

    @RequestMapping(value = "/assign", produces = "application/json", method = RequestMethod.POST)
    public AssignOrderResponse assignOrder(AssignOrderRequest request) {
        return deliveryPersonService.assignOrder(request);
    }

    @RequestMapping(value = "/status", produces = "application/json", method = RequestMethod.GET)
    public GetDeliveryPersonStatusResponse getDeliveryPersonStatus(GetDeliveryPersonStatusRequest request) {
        return deliveryPersonService.getDeliveryPersonStatus(request);
    }
}
