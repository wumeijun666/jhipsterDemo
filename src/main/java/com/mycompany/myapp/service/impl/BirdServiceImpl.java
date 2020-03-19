package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.BirdService;
import com.mycompany.myapp.domain.Bird;
import com.mycompany.myapp.repository.BirdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Bird}.
 */
@Service
@Transactional
public class BirdServiceImpl implements BirdService {

    private final Logger log = LoggerFactory.getLogger(BirdServiceImpl.class);

    private final BirdRepository birdRepository;

    public BirdServiceImpl(BirdRepository birdRepository) {
        this.birdRepository = birdRepository;
    }

    /**
     * Save a bird.
     *
     * @param bird the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Bird save(Bird bird) {
        log.debug("Request to save Bird : {}", bird);
        return birdRepository.save(bird);
    }

    /**
     * Get all the birds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Bird> findAll(Pageable pageable) {
        log.debug("Request to get all Birds");
        return birdRepository.findAll(pageable);
    }


    /**
     * Get one bird by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Bird> findOne(Long id) {
        log.debug("Request to get Bird : {}", id);
        return birdRepository.findById(id);
    }

    /**
     * Delete the bird by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bird : {}", id);
        birdRepository.deleteById(id);
    }
}
