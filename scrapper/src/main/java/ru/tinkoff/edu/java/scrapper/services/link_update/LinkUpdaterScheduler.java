package ru.tinkoff.edu.java.scrapper.services.link_update;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.tinkoff.edu.java.scrapper.services.web_service.UpdatableService;

@Slf4j
@Service
public class LinkUpdaterScheduler {

    private final List<UpdatableService> updatableServices;

    public LinkUpdaterScheduler(List<UpdatableService> updatableServices) {
        this.updatableServices = updatableServices;
    }

    @Scheduled(fixedDelayString = "${app.scheduler.interval}")
    void update() {
        updatableServices.forEach(UpdatableService::update);
        log.info("Links updated");
    }
}
