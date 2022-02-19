package com.openpayd.foreignexchange.controller;

import com.openpayd.foreignexchange.model.ConversionDetail;
import com.openpayd.foreignexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConversionListController
{
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping(value = "/conversion-list")
    public ResponseEntity<List<ConversionDetail>> getAllConversions(@RequestParam String date)
    {
        List<ConversionDetail> conversionResults = exchangeService.getAllConversions(date);

        return ResponseEntity.ok(conversionResults);
    }

    @GetMapping(value = "/conversion-detail")
    public ResponseEntity<ConversionDetail> getConversionDetail(@RequestParam String id)
    {
        ConversionDetail conversionDetail = exchangeService.getConversionDetail(id);

        return ResponseEntity.ok(conversionDetail);
    }
}
