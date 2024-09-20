package com.hr.common.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendNotificationCommand implements Serializable {
    private String orderCode;
    private String userEmail;
}
