package com.openpayd.foreignexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ConversionRequest
{
    private BigDecimal sourceAmount;
    private String sourceCurrency;
    private String targetCurrency;
}
