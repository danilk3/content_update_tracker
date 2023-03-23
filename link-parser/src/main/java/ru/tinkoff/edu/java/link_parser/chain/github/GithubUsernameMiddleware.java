package ru.tinkoff.edu.java.link_parser.chain.github;

import ru.tinkoff.edu.java.link_parser.chain.BaseMiddleware;

import java.net.URL;
import java.util.List;

public class GithubUsernameMiddleware extends BaseMiddleware {

    private final List<String> NOT_ALLOWED_USERNAMES = List.of(
            "organizations",
            "trending",
            "settings",
            "customer-stories",
            "login",
            "signup",
            "enterprise",
            "security",
            "marketplace",
            "features",
            "readme",
            "team",
            "codespaces",
            "collections",
            "events",
            "sponsors"
    );

    @Override
    public boolean check(URL url) {
        String username = url.getPath().split("/")[1];
        if (NOT_ALLOWED_USERNAMES.contains(username)) {
            return false;
        }
        return checkNext(url);
    }
}
