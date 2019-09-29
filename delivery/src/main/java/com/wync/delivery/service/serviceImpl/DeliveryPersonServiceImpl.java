package com.wync.delivery.service.serviceImpl;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wync.delivery.entities.AssignedOrder;
import com.wync.delivery.entities.DeliveryPerson;
import com.wync.delivery.entities.Order;
import com.wync.delivery.enums.DelegationStatus;
import com.wync.delivery.enums.DeliveryPersonStatus;
import com.wync.delivery.request.AssignOrderRequest;
import com.wync.delivery.request.GetDeliveryPersonStatusRequest;
import com.wync.delivery.response.AssignOrderResponse;
import com.wync.delivery.response.GetDeliveryPersonStatusResponse;
import com.wync.delivery.service.IDeliveryPersonService;

@Service
public class DeliveryPersonServiceImpl  implements IDeliveryPersonService {
    private static final ConcurrentHashMap<String, DeliveryPerson> deliveryPersons = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, AssignedOrder> assignedOrders = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {

        DeliveryPerson dp = new DeliveryPerson();
        dp.setMobileNo("8683932365");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);

        DeliveryPerson dp1 = new DeliveryPerson();
        dp.setMobileNo("8683932366");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);

        DeliveryPerson dp2 = new DeliveryPerson();
        dp.setMobileNo("8683932367");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);

        DeliveryPerson dp3 = new DeliveryPerson();
        dp.setMobileNo("8683932368");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);

        DeliveryPerson dp4 = new DeliveryPerson();
        dp.setMobileNo("8683932369");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);

        DeliveryPerson dp5 = new DeliveryPerson();
        dp.setMobileNo("8683932370");
        dp.setStatus(DeliveryPersonStatus.INACTIVE);
        deliveryPersons.put(dp.getMobileNo(), dp);
    }

    @Override
    public AssignOrderResponse assignOrder(AssignOrderRequest request) {
        AssignOrderResponse response = new AssignOrderResponse();
        synchronized (deliveryPersons) {
            DeliveryPerson deliveryPerson = deliveryPersons.get(request.getDeliveryPersonId());
            if(deliveryPerson.getStatus().equals(DeliveryPersonStatus.ACTIVE)) {
                response.setStatus(DelegationStatus.DECLINED);
            } else {
                deliveryPerson.setStatus(DeliveryPersonStatus.ACTIVE);
                synchronized (assignedOrders) {
                    AssignedOrder assignedOrder = new AssignedOrder();
                    assignedOrder.setDeliveryPersonId(request.getDeliveryPersonId());
                    assignedOrder.setOrderId(request.getOrderId());
                    assignedOrder.setExpectedDeliveryAt(getOrderDeliveryDate(request.getOrderId()));
                    assignedOrders.put(assignedOrder.getDeliveryPersonId(), assignedOrder);
                }
                response.setStatus(DelegationStatus.ACCEPTED);
            }
        }
        return response;
    }

    @Override
    public GetDeliveryPersonStatusResponse getDeliveryPersonStatus(GetDeliveryPersonStatusRequest request) {
        GetDeliveryPersonStatusResponse response = new GetDeliveryPersonStatusResponse();
        if(deliveryPersons.get(request.getDeliveryPersonId()).getStatus().equals(DeliveryPersonStatus.ACTIVE)) {
            AssignedOrder assignedOrder = assignedOrders.get(request.getDeliveryPersonId());
            if(assignedOrder != null) {
                response.setStatus(DeliveryPersonStatus.ACTIVE);
                response.setOrderId(assignedOrder.getOrderId());
            } else {
                response.setStatus(DeliveryPersonStatus.INACTIVE);
            }
        } else {
            response.setStatus(DeliveryPersonStatus.INACTIVE);
        }
        return response;
    }

    private Date getOrderDeliveryDate(int orderId) {
        RestTemplate restTemplate = new RestTemplate();
        String baseurl = "http://localhost:9090/orders/status?orderId=";
        Order order = restTemplate.getForObject(baseurl + orderId, Order.class);
        return order.getExpectedDeliveryAt();
    }

    private long getTimeToDeliverInMin(Order order) {
        Date currDate = new Date();
        long timeInMill = order.getExpectedDeliveryAt().getTime() - currDate.getTime();
        return timeInMill/(1000 * 60);
    }
}
