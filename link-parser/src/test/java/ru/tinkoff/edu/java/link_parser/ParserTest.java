package ru.tinkoff.edu.java.link_parser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.tinkoff.edu.java.link_parser.model.GithubParseResult;
import ru.tinkoff.edu.java.link_parser.model.StackoverflowParseResult;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParserTest {

    @ParameterizedTest
    @MethodSource("provideInvalidLinks")
    public void shouldReturnNullOnInvalidLink(String url) {
        Parser parser = new Parser(url);
        assertThat(parser.parse()).isNull();
    }

    private static Stream<Arguments> provideInvalidLinks() {
        return Stream.of(
                Arguments.of("dsaf"),
                Arguments.of(""),
                Arguments.of("https://stackoverflow.com/questions/tagged/javascript"),
                Arguments.of("https://stackoverflow.com/tags"),
                Arguments.of("https://stackoverflow.com/"),
                Arguments.of("hps://github.com/danilk3/SettleApi"),
                Arguments.of("https://gitdadhub.com/danilk3/SettleApi"),
                Arguments.of("https://github.com/"),
                Arguments.of("https://github.com/pulls")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidGitHubLinks")
    public void shouldReturnNotNullOnValidGitHubLink(String url, String username, String repoName) {
        Parser parser = new Parser(url);
        GithubParseResult githubParseResult = (GithubParseResult) parser.parse();
        assertThat(githubParseResult).isNotNull();
        assertThat(githubParseResult.repositoryName()).isEqualTo(repoName);
        assertThat(githubParseResult.username()).isEqualTo(username);
    }

    private static Stream<Arguments> provideValidGitHubLinks() {
        return Stream.of(
                Arguments.of("https://github.com/danilk3/test_task", "danilk3", "test_task"),
                Arguments.of("https://github.com/sanyarnd/tinkoff-java-course-2022", "sanyarnd", "tinkoff-java-course-2022")
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidStackoverflowLinks")
    public void shouldReturnNotNullOnValidStackoverflowLink(String url, Long questionId) {
        Parser parser = new Parser(url);
        StackoverflowParseResult githubParseResult = (StackoverflowParseResult) parser.parse();
        assertThat(githubParseResult).isNotNull();
        assertThat(githubParseResult.questionId()).isEqualTo(questionId);
    }

    private static Stream<Arguments> provideValidStackoverflowLinks() {
        return Stream.of(
                Arguments.of("https://stackoverflow.com/questions/75931843/regex-to-convert-video-from-custom-input-into-html", 75931843L),
                Arguments.of("https://stackoverflow.com/questions/75933366/c-polymorphism-and-type-specifics", 75933366L)
        );
    }
}
