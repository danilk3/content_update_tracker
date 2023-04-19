package ru.tinkoff.edu.java.jooq;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JooqCodegen {
    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql://localhost:5432/scrapper")
                        .withUser("tracker-user")
                        .withPassword("123456"))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withPackageName("ru.tinkoff.edu.java.scrapper.domain.jooq")
                                .withDirectory("scrapper/src/main/java")));
        GenerationTool.generate(configuration);
    }
}