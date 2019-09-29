package com.wync.delivery.request;

import lombok.Data;

@Data
public class AssignOrderRequest {
    private int orderId;

    private String deliveryPersonId;
}
