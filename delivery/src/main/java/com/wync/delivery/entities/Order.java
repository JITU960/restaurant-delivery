package com.wync.delivery.entities;

import java.util.Date;

import com.wync.delivery.enums.OrderStatus;

import lombok.Data;

@Data
public class Order {

    private int orderId;

    private OrderStatus status;

    private Date expectedDeliveryAt;

    private String deliveryAddress;
}
