package com.wync.rastaurant.response;

import com.wync.rastaurant.enums.OrderStatus;
import lombok.Data;

@Data
public class UpdateOrderStatusResponse {
    private OrderStatus orderStatus;

    public UpdateOrderStatusResponse(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
