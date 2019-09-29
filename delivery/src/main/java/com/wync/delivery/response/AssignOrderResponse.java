package com.wync.delivery.response;

import com.wync.delivery.enums.DelegationStatus;
import lombok.Data;

@Data
public class AssignOrderResponse {
    private DelegationStatus status;
}
