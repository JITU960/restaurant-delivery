package com.wync.rastaurant.response;

import com.wync.rastaurant.enums.OrderStatus;
import lombok.Data;

@Data
public class GetOrderStatusResponse {
    private int orderId;
    private OrderStatus orderStatus;
}
