package com.tthttl.customerservice;

import java.util.*;

public abstract class AbstractMapService<T> {

    protected Map<Long, T> map = new HashMap<>();

    protected T save(T entity, Long id) {
        map.put(id, entity);
        return entity;
    }

    protected Optional<T> findById(Long id) {
        T foundEntity = map.get(id);
        if (foundEntity == null) {
            return Optional.empty();
        }
        return Optional.of(foundEntity);
    }

    protected List<T> findAll() {
        return new ArrayList<>(map.values());
    }

    protected void delete(T entity) {
        map.values().remove(entity);
    }


}
