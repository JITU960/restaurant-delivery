package com.wync.rastaurant.request;

import com.wync.rastaurant.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusRequest {
    private int orderId;

    private OrderStatus orderStatus;
}
