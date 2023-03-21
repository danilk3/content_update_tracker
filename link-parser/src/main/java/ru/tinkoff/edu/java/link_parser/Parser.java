package ru.tinkoff.edu.java.link_parser;

import ru.tinkoff.edu.java.link_parser.chain.BaseMiddleware;
import ru.tinkoff.edu.java.link_parser.chain.github.GithubUsernameMiddleware;
import ru.tinkoff.edu.java.link_parser.chain.result.GithubResultGetter;
import ru.tinkoff.edu.java.link_parser.chain.result.StackoverflowResultGetter;
import ru.tinkoff.edu.java.link_parser.model.ParseResult;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private enum UrlType {
        STACKOVERFLOW,
        GITHUB
    }

    private final Map<String, UrlType> regexMap = Map.of(
            "^https://(www.)?github.com/[a-zA-Z0-9-]+/[a-zA-Z0-9-_]+/?$", UrlType.GITHUB,
            "^https://(www.)?stackoverflow.com/questions/[0-9]+/[a-zA-Z0-9-_]+/?$", UrlType.STACKOVERFLOW
    );

    private final Map<UrlType, BaseMiddleware> middlewareMap = Map.of(
            UrlType.GITHUB, BaseMiddleware.link(List.of(new GithubUsernameMiddleware()), new GithubResultGetter()),
            UrlType.STACKOVERFLOW, BaseMiddleware.link(List.of(), new StackoverflowResultGetter())
    );

    private final String stringUrl;

    public Parser(String stringUrl) {
        this.stringUrl = stringUrl;
    }

    public ParseResult parse() {
        BaseMiddleware middleware = getMiddleware();
        try {
            URL url = new URL(stringUrl);
            if (middleware != null && middleware.check(url)) {
                return middleware.getParseResult(url);
            }
        } catch (MalformedURLException e) {
            return null;
        }
        return null;
    }

    private BaseMiddleware getMiddleware() {
        Pattern pattern;
        for (String regexValue : regexMap.keySet()) {
            pattern = Pattern.compile(regexValue);
            Matcher matcher = pattern.matcher(stringUrl);
            if (matcher.find()) {
                return middlewareMap.get(regexMap.get(regexValue));
            }
        }
        return null;
    }
}
