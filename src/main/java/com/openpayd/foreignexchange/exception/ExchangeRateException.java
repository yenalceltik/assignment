package com.openpayd.foreignexchange.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExchangeRateException extends RuntimeException
{
    private HttpStatus httpStatus;

    public ExchangeRateException(String message, HttpStatus httpStatus)
    {
        super(message);
        this.httpStatus = httpStatus;
    }
}
