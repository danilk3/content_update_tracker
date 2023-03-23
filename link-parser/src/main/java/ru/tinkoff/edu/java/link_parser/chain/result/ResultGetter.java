package ru.tinkoff.edu.java.link_parser.chain.result;

import ru.tinkoff.edu.java.link_parser.model.ParseResult;

import java.net.URL;

public interface ResultGetter {

    public ParseResult getParseResult(URL url);

}
