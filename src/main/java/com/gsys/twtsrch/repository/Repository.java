package com.gsys.twtsrch.repository;

import java.util.List;

public interface Repository<T> {
    Iterable<T> saveAll(List<T> entities);
    List<T> findAll();
}
