package ru.tinkoff.edu.java.bot.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

@RestControllerAdvice
public class BotExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiErrorResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        return new ApiErrorResponse(
            "Missed request body",
            HttpStatus.BAD_REQUEST.toString(),
            "HttpMessageNotReadableException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return new ApiErrorResponse(
            "Not valid request body",
            HttpStatus.BAD_REQUEST.toString(),
            "MethodArgumentNotValidException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

}
