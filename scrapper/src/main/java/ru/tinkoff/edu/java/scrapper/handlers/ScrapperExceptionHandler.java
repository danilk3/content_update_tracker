package ru.tinkoff.edu.java.scrapper.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.tinkoff.edu.java.scrapper.dto.api.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatAlreadyExistsException;
import ru.tinkoff.edu.java.scrapper.exceptions.ChatNotExistsException;
import ru.tinkoff.edu.java.scrapper.exceptions.LinkNotExistsException;

@RestControllerAdvice
public class ScrapperExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MissingRequestHeaderException.class})
    public ApiErrorResponse handleMissingRequestHeaderException(MissingRequestHeaderException exception) {
        return new ApiErrorResponse(
            "Missed request header",
            HttpStatus.BAD_REQUEST.toString(),
            "MissingRequestHeaderException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ApiErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return new ApiErrorResponse(
            "Argument mismatch",
            HttpStatus.BAD_REQUEST.toString(),
            "MethodArgumentTypeMismatchException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LinkNotExistsException.class)
    public ApiErrorResponse handleLinkNotExistsException(LinkNotExistsException exception) {
        return new ApiErrorResponse(
            "Link does not exists",
            HttpStatus.NOT_FOUND.toString(),
            "LinkNotExistsException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ChatNotExistsException.class)
    public ApiErrorResponse handleChatNotExistsException(ChatNotExistsException exception) {
        return new ApiErrorResponse(
            "Chat does not exists",
            HttpStatus.NOT_FOUND.toString(),
            "ChatNotExistsException",
            exception.getMessage(),
            exception.getStackTrace()
        );
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ChatAlreadyExistsException.class)
    public ApiErrorResponse handleChatAlreadyExistsException(ChatAlreadyExistsException exception) {
        return new ApiErrorResponse(
            "Chat already exists",
            HttpStatus.CONFLICT.toString(),
            "ChatAlreadyExistsException",
            exception.getMessage(),
            exception.getStackTrace()
        );

    }
}
