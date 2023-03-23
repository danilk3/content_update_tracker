package ru.tinkoff.edu.java.link_parser.model;

public record GithubParseResult(String username, String repositoryName) implements ParseResult {
}
