package com.openpayd.foreignexchange.service.client;

import com.openpayd.foreignexchange.dto.ExchangeRateRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FixerIOServiceTest
{

    @Test
    void exchangeRate()
    {
        FixerIOService fixerIOService = mock(FixerIOService.class);

        ExchangeRateRequest request = new ExchangeRateRequest("EUR", "TRY");
        when(fixerIOService.exchangeRate(request)).thenReturn(BigDecimal.valueOf(15));

        BigDecimal rate = fixerIOService.exchangeRate(request);

        assertThat(rate).isEqualTo(BigDecimal.valueOf(15));

    }
}