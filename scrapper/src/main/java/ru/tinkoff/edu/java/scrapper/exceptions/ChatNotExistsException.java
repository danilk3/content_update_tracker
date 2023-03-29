package ru.tinkoff.edu.java.scrapper.exceptions;

public class ChatNotExistsException extends Exception {

    public ChatNotExistsException(Long id) {
        super(String.format("Chat with id = %d does not exists", id));
    }
}
