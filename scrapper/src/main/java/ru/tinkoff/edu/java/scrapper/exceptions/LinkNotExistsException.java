package ru.tinkoff.edu.java.scrapper.exceptions;

public class LinkNotExistsException extends Exception {
    public LinkNotExistsException(String link) {
        super(String.format("Link - %s does not exists", link));
    }
}
