package com.ajromero.common.service;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.common.persistence.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IService<T extends Serializable, TD> {

    List<T> findAll();

    Optional<T> findById(TD id);

    T save(final T resource);

    // update

    T update(final T resource);

    // delete

}
