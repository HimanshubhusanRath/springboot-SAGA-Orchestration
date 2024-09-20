package com.hr.order.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderRequest {
    private String userEmail;
    private String paymentInfo;
}
