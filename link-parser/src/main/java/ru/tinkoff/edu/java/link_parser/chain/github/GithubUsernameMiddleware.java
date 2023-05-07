package ru.tinkoff.edu.java.link_parser.chain.github;

import java.net.URL;
import java.util.List;
import ru.tinkoff.edu.java.link_parser.chain.BaseMiddleware;

public class GithubUsernameMiddleware extends BaseMiddleware {

    private final List<String> notAllowedUsernames = List.of(
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
        if (notAllowedUsernames.contains(username)) {
            return false;
        }
        return checkNext(url);
    }
}
