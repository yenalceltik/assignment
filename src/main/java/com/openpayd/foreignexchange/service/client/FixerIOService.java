package com.openpayd.foreignexchange.service.client;


import com.openpayd.foreignexchange.dto.ExchangeRateRequest;
import com.openpayd.foreignexchange.dto.ExchangeRateResponse;
import com.openpayd.foreignexchange.exception.ExchangeRateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class FixerIOService
{
    @Value("${fixer.api.key}")
    private String apiKey;

    @Value("${base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public FixerIOService(){
        this.restTemplate = new RestTemplate();
    }

    public BigDecimal exchangeRate(ExchangeRateRequest request)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String historicalEndpoint = baseUrl + "latest";
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(historicalEndpoint)
                .queryParam("access_key", "{access_key}")
                .queryParam("base", "{base}")
                .queryParam("symbols", "{symbols}")
                .encode()
                .toUriString();
        Map<String, String> params = new HashMap<>();
        params.put("base", request.getFrom());
        params.put("symbols", request.getTo());
        params.put("access_key", apiKey);

        HttpEntity entity = new HttpEntity(headers);

        log.warn("Request is started.");
        ResponseEntity<ExchangeRateResponse> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                ExchangeRateResponse.class,
                params);
        log.warn("Request is successfully ended.");
        if (response.getStatusCode() != HttpStatus.OK)
        {
            throw new ExchangeRateException("Exchange service exception.", response.getStatusCode());
        }
        if (response.getBody() != null && !response.getBody().isSuccess())
        {
            throw new ExchangeRateException("Fixer IO Exception Info: " + response.getBody().getError().getInfo(), HttpStatus.resolve(response.getBody().getError().getCode()));
        }

        return Objects.requireNonNull(response.getBody()).getRates().get(request.getTo());
    }
}
