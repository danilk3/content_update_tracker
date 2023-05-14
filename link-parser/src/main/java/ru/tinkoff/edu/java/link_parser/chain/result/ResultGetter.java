package ru.tinkoff.edu.java.link_parser.chain.result;

import java.net.URL;
import ru.tinkoff.edu.java.link_parser.model.ParseResult;

public interface ResultGetter {

    ParseResult getParseResult(URL url);

}
