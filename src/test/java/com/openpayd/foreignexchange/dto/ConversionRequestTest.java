package com.openpayd.foreignexchange.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ConversionRequestTest
{
    @Test
    @DisplayName("creating conversion request")
    void creatingConversionRequest() {

        ConversionRequest conversionRequest = new ConversionRequest(BigDecimal.valueOf(50),"TRY", "EUR");

        assertEquals(BigDecimal.valueOf(50), conversionRequest.getSourceAmount());
        assertEquals("TRY", conversionRequest.getSourceCurrency());
        assertEquals("EUR", conversionRequest.getTargetCurrency());
    }
}