package com.ajromero.common.service;

import com.ajromero.common.persistence.IEntity;

public interface IService<T extends IEntity> extends IOperations<T> {

    T findByEmail(String email);

    boolean existsByEmail(String email);

}
