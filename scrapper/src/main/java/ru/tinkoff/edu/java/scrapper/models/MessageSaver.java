package ru.tinkoff.edu.java.scrapper.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageSaver<T> {

    private T value;

    private String message;
}
