package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Bird;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Bird}.
 */
public interface BirdService {

    /**
     * Save a bird.
     *
     * @param bird the entity to save.
     * @return the persisted entity.
     */
    Bird save(Bird bird);

    /**
     * Get all the birds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Bird> findAll(Pageable pageable);


    /**
     * Get the "id" bird.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Bird> findOne(Long id);

    /**
     * Delete the "id" bird.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
