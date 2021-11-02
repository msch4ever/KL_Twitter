package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.PersistenceEntity;

import java.util.Optional;
import java.util.Set;

public interface GenericDao <T extends PersistenceEntity> {

    Long save(T model);

    Set<T> getAll();

    Optional<T> findById(Long id);

    void update(T model);

    void deleteById(Long id);

}
