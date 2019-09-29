package com.wync.delivery.response;

import com.wync.delivery.enums.DeliveryPersonStatus;
import lombok.Data;

@Data
public class GetDeliveryPersonStatusResponse {
    private DeliveryPersonStatus status;

    private int orderId;

    private long timeToDeliverInMinute;
}
