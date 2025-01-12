package com.archsoft.model.payment;

import com.archsoft.model.AbstractDocument;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Payment extends AbstractDocument {

    private Date dateTime;
    private String orderId;
    private String creditCardNumber;
    private String nameOnCard;
    private String expiryDate;
    private String securityCode;
    private BigDecimal value;
    private String status;
}
