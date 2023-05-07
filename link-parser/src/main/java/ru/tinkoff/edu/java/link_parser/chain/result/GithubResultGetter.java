package ru.tinkoff.edu.java.link_parser.chain.result;

import java.net.URL;
import ru.tinkoff.edu.java.link_parser.model.GithubParseResult;

public class GithubResultGetter implements ResultGetter {

    @Override
    public GithubParseResult getParseResult(URL url) {
        String[] pathValues = url.getPath().split("/");
        return new GithubParseResult(pathValues[1], pathValues[2]);
    }
}
