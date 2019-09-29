package com.wync.delivery.entities;

import com.wync.delivery.enums.DeliveryPersonStatus;
import lombok.Data;

@Data
public class DeliveryPerson {
    private String name;

    private String mobileNo;

    private DeliveryPersonStatus status;
}
