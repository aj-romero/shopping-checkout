package com.ajromero.common.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IServiceBasic<T extends Serializable, D> {
    List<T> findAll();

    Optional<T> findById(D id);
}
