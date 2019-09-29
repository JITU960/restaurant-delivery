package com.wync.delivery.enums;

public enum OrderStatus {
    CONFIRMED("confirmed"), DELIVERY_PERSON_ASSIGNED("delivery_person_assigned"), PICKED_UP("picked_up"), DELIVERED("delivered"), CANCELLED("cancelled");

    private OrderStatus(String status) {
        this.status = status;
    }

    private String status;
}
