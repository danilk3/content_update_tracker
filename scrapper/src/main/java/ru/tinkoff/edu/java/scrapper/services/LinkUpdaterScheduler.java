package ru.tinkoff.edu.java.scrapper.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LinkUpdaterScheduler {

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    void update() {
        log.info("Some log...");
    }
}
