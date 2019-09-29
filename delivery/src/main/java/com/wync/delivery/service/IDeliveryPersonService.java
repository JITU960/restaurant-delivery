package com.wync.delivery.service;

import com.wync.delivery.request.AssignOrderRequest;
import com.wync.delivery.request.GetDeliveryPersonStatusRequest;
import com.wync.delivery.response.AssignOrderResponse;
import com.wync.delivery.response.GetDeliveryPersonStatusResponse;

public interface IDeliveryPersonService {
    AssignOrderResponse assignOrder(AssignOrderRequest request);
    GetDeliveryPersonStatusResponse getDeliveryPersonStatus(GetDeliveryPersonStatusRequest request);
}
