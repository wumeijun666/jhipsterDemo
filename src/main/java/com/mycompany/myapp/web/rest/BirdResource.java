package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Bird;
import com.mycompany.myapp.service.BirdService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.Bird}.
 */
@RestController
@RequestMapping("/api")
public class BirdResource {

    private final Logger log = LoggerFactory.getLogger(BirdResource.class);

    private static final String ENTITY_NAME = "bird";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BirdService birdService;

    public BirdResource(BirdService birdService) {
        this.birdService = birdService;
    }

    /**
     * {@code POST  /birds} : Create a new bird.
     *
     * @param bird the bird to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bird, or with status {@code 400 (Bad Request)} if the bird has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/birds")
    public ResponseEntity<Bird> dreateBird(@Valid @RequestBody Bird bird) throws URISyntaxException {
        log.debug("REST request to save Bird : {}", bird);
        if (bird.getId() != null) {
            throw new BadRequestAlertException("A new bird cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bird result = birdService.save(bird);
        return ResponseEntity.created(new URI("/api/birds/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /birds} : Updates an existing bird.
     *
     * @param bird the bird to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bird,
     * or with status {@code 400 (Bad Request)} if the bird is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bird couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/birds")
    public ResponseEntity<Bird> updateBird(@Valid @RequestBody Bird bird) throws URISyntaxException {
        log.debug("REST request to update Bird : {}", bird);
        if (bird.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Bird result = birdService.save(bird);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bird.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /birds} : get all the birds.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of birds in body.
     */
    @GetMapping("/birds")
    public ResponseEntity<List<Bird>> getAllBirds(Pageable pageable) {
        log.debug("REST request to get a page of Birds");
        Page<Bird> page = birdService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /birds/:id} : get the "id" bird.
     *
     * @param id the id of the bird to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bird, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/birds/{id}")
    public ResponseEntity<Bird> getBird(@PathVariable Long id) {
        log.debug("REST request to get Bird : {}", id);
        Optional<Bird> bird = birdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(bird);
    }

    /**
     * {@code DELETE  /birds/:id} : delete the "id" bird.
     *
     * @param id the id of the bird to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/birds/{id}")
    public ResponseEntity<Void> deleteBird(@PathVariable Long id) {
        log.debug("REST request to delete Bird : {}", id);
        birdService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
