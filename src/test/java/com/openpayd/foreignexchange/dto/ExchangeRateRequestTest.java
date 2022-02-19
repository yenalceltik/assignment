package com.openpayd.foreignexchange.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRateRequestTest
{
    @Test
    @DisplayName("creating rate request")
    void creatingRateRequest() {
        ExchangeRateRequest request = new ExchangeRateRequest("USD", "TRY");

        assertEquals("USD", request.getFrom());
        assertEquals("TRY", request.getTo());
    }
}