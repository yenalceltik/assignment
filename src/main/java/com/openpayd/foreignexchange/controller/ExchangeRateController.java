package com.openpayd.foreignexchange.controller;

import com.openpayd.foreignexchange.dto.ExchangeRateRequest;
import com.openpayd.foreignexchange.dto.ExchangeRateResult;
import com.openpayd.foreignexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeRateController
{

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(value = "/exchange-rate")
    public ResponseEntity<ExchangeRateResult> refreshImageManagement(@RequestBody ExchangeRateRequest request)
    {
        return exchangeService.getExchangeRate(request);
    }
}
