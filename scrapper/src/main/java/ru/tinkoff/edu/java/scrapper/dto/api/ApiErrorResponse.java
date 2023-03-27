package ru.tinkoff.edu.java.scrapper.dto.api;

public record ApiErrorResponse(String description,
                               String code,
                               String exceptionName,
                               String exceptionMessage,
                               StackTraceElement[] stacktrace) {
}
