package com.openpayd.foreignexchange.service;

import com.openpayd.foreignexchange.dao.ConversionDetailDAO;
import com.openpayd.foreignexchange.dto.ConversionRequest;
import com.openpayd.foreignexchange.dto.ConversionResult;
import com.openpayd.foreignexchange.dto.ExchangeRateRequest;
import com.openpayd.foreignexchange.dto.ExchangeRateResult;
import com.openpayd.foreignexchange.exception.ExchangeRateException;
import com.openpayd.foreignexchange.model.ConversionDetail;
import com.openpayd.foreignexchange.service.client.FixerIOService;
import com.openpayd.foreignexchange.util.DateFormatUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExchangeService
{

    @Setter
    @Resource(name = "conversionDetailDAO")
    private ConversionDetailDAO conversionDetailDAO;

    @Autowired
    private FixerIOService fixerIOService;

    public BigDecimal exchangeRate(ExchangeRateRequest request)
    {
        return fixerIOService.exchangeRate(request);
    }

    public ResponseEntity<ExchangeRateResult> getExchangeRate(ExchangeRateRequest request)
    {
        BigDecimal rate = this.exchangeRate(request);
        ExchangeRateResult exchangeRateResult = new ExchangeRateResult(request.getFrom(), request.getTo(), rate);
        return ResponseEntity.ok(exchangeRateResult);

    }

    public ResponseEntity<ConversionResult> currencyConversion(ConversionRequest request)
    {
        ExchangeRateRequest exchangeRequest = new ExchangeRateRequest(request.getSourceCurrency(), request.getTargetCurrency());

        BigDecimal rate = this.exchangeRate(exchangeRequest);
        BigDecimal targetAmount = request.getSourceAmount().multiply(rate);
        String transactionId = this.saveConversionDetailAndGetId(request.getSourceCurrency(), request.getTargetCurrency(), targetAmount);
        ConversionResult result = new ConversionResult(targetAmount, transactionId);

        return ResponseEntity.ok(result);
    }

    private String saveConversionDetailAndGetId(String sourceCurrency, String targetCurrency, BigDecimal targetAmount)
    {
        ConversionDetail conversionDetail = new ConversionDetail();
        conversionDetail.setSourceCurrency(sourceCurrency);
        conversionDetail.setTargetCurrency(targetCurrency);
        conversionDetail.setAmountConverted(targetAmount);
        conversionDetail.setCreateTime(DateFormatUtil.getCurrentIsoFormattedDate());

        try
        {
            conversionDetail = conversionDetailDAO.saveAndFlush(conversionDetail);
        }
        catch (Exception e)
        {
            throw new ExchangeRateException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return conversionDetail.getId();
    }

    public List<ConversionDetail> getAllConversions(String date)
    {
        List<ConversionDetail> conversionDetails = conversionDetailDAO.findAll();

        return filterConversions(date, conversionDetails);
    }

    public List<ConversionDetail> filterConversions(String date, List<ConversionDetail> conversionDetails)
    {
        return conversionDetails.stream()
                .filter(conversionDetail -> conversionDetail.getCreateTime().equals(date))
                .collect(Collectors.toList());
    }

    public ConversionDetail getConversionDetail(String id)
    {
        Optional<ConversionDetail> conversionDetailOptional = conversionDetailDAO.findById(id);
        return conversionDetailOptional.orElse(null);
    }
}
