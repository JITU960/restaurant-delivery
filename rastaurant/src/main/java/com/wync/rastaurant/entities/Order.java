package com.wync.rastaurant.entities;

import com.wync.rastaurant.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private int orderId;

    private OrderStatus status;

    private Date expectedDeliveryAt;

    private String deliveryAddress;
}
