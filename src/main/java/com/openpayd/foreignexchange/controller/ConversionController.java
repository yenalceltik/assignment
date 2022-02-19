package com.openpayd.foreignexchange.controller;

import com.openpayd.foreignexchange.dto.ConversionRequest;
import com.openpayd.foreignexchange.dto.ConversionResult;
import com.openpayd.foreignexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController
{
    @Autowired
    private ExchangeService exchangeService;

    @PostMapping(value = "/convert")
    public ResponseEntity<ConversionResult> convert(@RequestBody ConversionRequest request){
        return exchangeService.currencyConversion(request);
    }
}
