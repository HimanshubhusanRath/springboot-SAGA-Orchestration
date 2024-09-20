package com.hr.common.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderCommand implements Serializable {
    @TargetAggregateIdentifier
    private String orderCode;
    private String paymentInfo;
    private String userEmail;
}
