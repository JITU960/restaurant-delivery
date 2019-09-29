package com.wync.rastaurant.response;

import com.wync.rastaurant.enums.OrderStatus;
import lombok.Data;

@Data
public class PlaceOrderResponse {
    private int orderId;

    private OrderStatus status;

    public PlaceOrderResponse(int orderId, OrderStatus status) {
        this.orderId = orderId;
        this.status = status;
    }
}
