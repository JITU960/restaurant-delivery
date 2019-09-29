package com.wync.delivery.entities;

import lombok.Data;

import java.util.Date;

@Data
public class AssignedOrder {
    private String deliveryPersonId;

    private int orderId;

    private Date expectedDeliveryAt;
}
