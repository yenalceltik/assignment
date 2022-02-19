package com.openpayd.foreignexchange.service;

import com.openpayd.foreignexchange.model.ConversionDetail;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExchangeServiceTest
{

    @Test
    void getAllConversions()
    {
        ExchangeService exchangeService = mock(ExchangeService.class);

        ConversionDetail conversionDetail = new ConversionDetail();
        conversionDetail.setId("ID");
        conversionDetail.setSourceCurrency("EUR");
        conversionDetail.setTargetCurrency("TRY");
        conversionDetail.setAmountConverted(BigDecimal.valueOf(50));
        conversionDetail.setCreateTime("2022-02-02");

        when(exchangeService.getAllConversions("2022-02-02")).thenReturn(Lists.newArrayList(conversionDetail));

        List<ConversionDetail> details = exchangeService.getAllConversions( "2022-02-02");

        assertThat(details).isEqualTo(Lists.newArrayList(conversionDetail));

    }
}