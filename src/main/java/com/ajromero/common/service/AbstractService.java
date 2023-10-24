package com.ajromero.common.service;

import com.ajromero.common.persistence.IEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


/*public abstract class AbstractService<T extends IEntity> implements IService<T> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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


    protected abstract JpaRepository<T, Long> getDao();

}*/
