package com.openpayd.foreignexchange.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = {ExchangeRateException.class})
    public ResponseEntity<Object> handleExchangeRateException(ExchangeRateException ex)
    {
        logger.error("error: ", ex);
        ErrorExceptionResponse response = new ErrorExceptionResponse();
        response.setStatus((ex.getHttpStatus().value()));
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorExceptionResponse response = new ErrorExceptionResponse();
        response.setStatus(status.value());
        response.setMessage(ex.getBindingResult().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
