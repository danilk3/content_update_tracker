package ru.tinkoff.edu.java.link_parser.chain;

import java.net.URL;
import java.util.List;
import ru.tinkoff.edu.java.link_parser.chain.result.ResultGetter;
import ru.tinkoff.edu.java.link_parser.model.ParseResult;

public abstract class BaseMiddleware {

    private BaseMiddleware next;

    private ResultGetter resultGetter;

    public static BaseMiddleware link(List<BaseMiddleware> middlewares, ResultGetter resultGetter) {

        if (middlewares.size() == 0) {
            BaseMiddleware middleware = new BaseMiddleware() {
                @Override
                public boolean check(URL url) {
                    return true;
                }
            };
            middleware.resultGetter = resultGetter;
            return middleware;
        }

        BaseMiddleware head = middlewares.get(0);
        head.resultGetter = resultGetter;

        for (int i = 1; i < middlewares.size(); i++) {
            BaseMiddleware currentMiddleware = middlewares.get(i);
            head.next = currentMiddleware;
            head = currentMiddleware;
        }

        return head;
    }

    public ParseResult getParseResult(URL url) {
        return resultGetter.getParseResult(url);
    }

    public abstract boolean check(URL url);

    protected boolean checkNext(URL url) {
        if (next == null) {
            return true;
        }
        return next.check(url);
    }
}
