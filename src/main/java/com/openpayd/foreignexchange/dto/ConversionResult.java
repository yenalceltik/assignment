package com.openpayd.foreignexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ConversionResult
{
    private BigDecimal targetAmount;
    private String transactionId;

}
