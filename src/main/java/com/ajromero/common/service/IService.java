package com.ajromero.common.service;

import com.ajromero.common.interfaces.IDto;
import com.ajromero.common.persistence.IEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IService<T extends Serializable, TD> extends IServiceBasic<T,TD> {


    T save(final T resource);

    // update

    T update(Long id,final T resource);

    // delete

}
