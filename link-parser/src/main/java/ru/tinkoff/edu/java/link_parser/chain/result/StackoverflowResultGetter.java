package ru.tinkoff.edu.java.link_parser.chain.result;

import java.net.URL;
import ru.tinkoff.edu.java.link_parser.model.ParseResult;
import ru.tinkoff.edu.java.link_parser.model.StackoverflowParseResult;

public class StackoverflowResultGetter implements ResultGetter {
    @Override
    public ParseResult getParseResult(URL url) {
        String[] pathValues = url.getPath().split("/");
        return new StackoverflowParseResult(Long.parseLong(pathValues[2]));
    }
}
