package ru.tinkoff.edu.java.scrapper.domain.updater;

import java.util.List;

public interface UpdatableRepository<T, U> {

    List<T> findOldUpdated();

    T update(U dto, Long linkId);

}
