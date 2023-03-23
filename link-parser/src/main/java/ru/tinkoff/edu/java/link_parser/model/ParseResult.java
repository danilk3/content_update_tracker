package ru.tinkoff.edu.java.link_parser.model;

public sealed interface ParseResult permits GithubParseResult, StackoverflowParseResult {
}
