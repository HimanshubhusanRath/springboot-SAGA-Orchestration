package com.hr.order.controller;

import com.hr.common.commands.CreateOrderCommand;
import com.hr.order.requests.OrderRequest;
import com.hr.order.saga.OrderSaga;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CommandGateway commandGateway;
    @PostMapping("/place")
    public ResponseEntity<String> placeOrder(@RequestBody final OrderRequest orderRequest) {
        final String uid = UUID.randomUUID().toString();
        commandGateway.send(new CreateOrderCommand(uid,orderRequest.getPaymentInfo(), orderRequest.getUserEmail()));
        return ResponseEntity.ok("Order Placed");
    }

}
