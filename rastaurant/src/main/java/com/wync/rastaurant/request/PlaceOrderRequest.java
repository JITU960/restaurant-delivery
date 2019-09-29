package com.wync.rastaurant.request;

import lombok.Data;

@Data
public class PlaceOrderRequest {
    private int itemId;

    private int noOfItems;

    private String deliveryAddress;
}
