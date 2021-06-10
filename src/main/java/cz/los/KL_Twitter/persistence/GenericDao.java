package cz.los.KL_Twitter.persistence;

import cz.los.KL_Twitter.model.PersistenceEntity;

import java.util.Optional;
import java.util.Set;

public interface GenericDao <T extends PersistenceEntity> {

    // CREATE
    Long save(T model);
    // READ
    Set<T> getAll();
    Optional<T> findById(Long id);
    // UPDATE
    void updateById(Long id, T model);
    // DELETE
    void deleteById(Long id);

}
