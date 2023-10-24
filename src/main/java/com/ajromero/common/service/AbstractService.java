package com.ajromero.common.service;

import com.ajromero.common.persistence.IEntity;
import com.ajromero.common.persistence.dao.IEntityEmail;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


public abstract class AbstractService<T extends IEntity> implements IService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public T findOne(final long id) {
        return getDao().findById(id)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return Lists.newArrayList(getDao().findAll());
    }


    @Override
    @Transactional
    public T create(final T entity) {
        logger.debug(getClass() + " Service >> Saving ");
        Objects.requireNonNull(entity);

        return getDao().save(entity);
    }

    @Override
    @Transactional
    public void update(final T entity) {
        logger.debug(getClass() + " Service >> Updating ");
        Objects.requireNonNull(entity);

        getDao().save(entity);
    }


    protected abstract IEntityEmail<T> getDao();

    @Override
    public T findByEmail(String email) {
        return getDao().findByEmail(email);
    }


    @Override
    public boolean existsByEmail(String email) {
        return getDao().existsByEmail(email);
    }
}
