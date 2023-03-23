package ru.tinkoff.edu.java.link_parser.chain.result;

import ru.tinkoff.edu.java.link_parser.model.GithubParseResult;

import java.net.URL;

public class GithubResultGetter implements ResultGetter {

    @Override
    public GithubParseResult getParseResult(URL url) {
        String[] pathValues = url.getPath().split("/");
        return new GithubParseResult(pathValues[1], pathValues[2]);
    }
}
