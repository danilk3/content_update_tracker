package ru.tinkoff.edu.java.scrapper.exceptions;

public class ChatAlreadyExistsException extends Exception {
    public ChatAlreadyExistsException(Long id) {
        super(String.format("Chat with id = %d already exists", id));
    }
}
