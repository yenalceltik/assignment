package com.openpayd.foreignexchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRateRequest
{
    private String from;
    private String to;
}
