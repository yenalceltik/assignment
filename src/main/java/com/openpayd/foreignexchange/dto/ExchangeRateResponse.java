package com.openpayd.foreignexchange.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExchangeRateResponse extends BaseResponseModel
{
    private long timestamp;
    private boolean historical;
    private String date;
    private String base;
    private Map<String, BigDecimal> rates;
}
