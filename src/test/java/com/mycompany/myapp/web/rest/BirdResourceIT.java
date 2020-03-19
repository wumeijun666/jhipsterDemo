package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhipsterDemoApp;
import com.mycompany.myapp.domain.Bird;
import com.mycompany.myapp.repository.BirdRepository;
import com.mycompany.myapp.service.BirdService;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BirdResource} REST controller.
 */
@SpringBootTest(classes = JhipsterDemoApp.class)
public class BirdResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ADD = "AAAAAAAAAA";
    private static final String UPDATED_ADD = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 18;
    private static final Integer UPDATED_AGE = 19;

    @Autowired
    private BirdRepository birdRepository;

    @Autowired
    private BirdService birdService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restBirdMockMvc;

    private Bird bird;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BirdResource birdResource = new BirdResource(birdService);
        this.restBirdMockMvc = MockMvcBuilders.standaloneSetup(birdResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bird createEntity(EntityManager em) {
        Bird bird = new Bird()
            .name(DEFAULT_NAME)
            .add(DEFAULT_ADD)
            .age(DEFAULT_AGE);
        return bird;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bird createUpdatedEntity(EntityManager em) {
        Bird bird = new Bird()
            .name(UPDATED_NAME)
            .add(UPDATED_ADD)
            .age(UPDATED_AGE);
        return bird;
    }

    @BeforeEach
    public void initTest() {
        bird = createEntity(em);
    }

    @Test
    @Transactional
    public void createBird() throws Exception {
        int databaseSizeBeforeCreate = birdRepository.findAll().size();

        // Create the Bird
        restBirdMockMvc.perform(post("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isCreated());

        // Validate the Bird in the database
        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeCreate + 1);
        Bird testBird = birdList.get(birdList.size() - 1);
        assertThat(testBird.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBird.getAdd()).isEqualTo(DEFAULT_ADD);
        assertThat(testBird.getAge()).isEqualTo(DEFAULT_AGE);
    }

    @Test
    @Transactional
    public void createBirdWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = birdRepository.findAll().size();

        // Create the Bird with an existing ID
        bird.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBirdMockMvc.perform(post("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isBadRequest());

        // Validate the Bird in the database
        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = birdRepository.findAll().size();
        // set the field null
        bird.setName(null);

        // Create the Bird, which fails.

        restBirdMockMvc.perform(post("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isBadRequest());

        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAddIsRequired() throws Exception {
        int databaseSizeBeforeTest = birdRepository.findAll().size();
        // set the field null
        bird.setAdd(null);

        // Create the Bird, which fails.

        restBirdMockMvc.perform(post("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isBadRequest());

        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIsRequired() throws Exception {
        int databaseSizeBeforeTest = birdRepository.findAll().size();
        // set the field null
        bird.setAge(null);

        // Create the Bird, which fails.

        restBirdMockMvc.perform(post("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isBadRequest());

        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBirds() throws Exception {
        // Initialize the database
        birdRepository.saveAndFlush(bird);

        // Get all the birdList
        restBirdMockMvc.perform(get("/api/birds?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bird.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].add").value(hasItem(DEFAULT_ADD)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }
    
    @Test
    @Transactional
    public void getBird() throws Exception {
        // Initialize the database
        birdRepository.saveAndFlush(bird);

        // Get the bird
        restBirdMockMvc.perform(get("/api/birds/{id}", bird.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bird.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.add").value(DEFAULT_ADD))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE));
    }

    @Test
    @Transactional
    public void getNonExistingBird() throws Exception {
        // Get the bird
        restBirdMockMvc.perform(get("/api/birds/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBird() throws Exception {
        // Initialize the database
        birdService.save(bird);

        int databaseSizeBeforeUpdate = birdRepository.findAll().size();

        // Update the bird
        Bird updatedBird = birdRepository.findById(bird.getId()).get();
        // Disconnect from session so that the updates on updatedBird are not directly saved in db
        em.detach(updatedBird);
        updatedBird
            .name(UPDATED_NAME)
            .add(UPDATED_ADD)
            .age(UPDATED_AGE);

        restBirdMockMvc.perform(put("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBird)))
            .andExpect(status().isOk());

        // Validate the Bird in the database
        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeUpdate);
        Bird testBird = birdList.get(birdList.size() - 1);
        assertThat(testBird.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBird.getAdd()).isEqualTo(UPDATED_ADD);
        assertThat(testBird.getAge()).isEqualTo(UPDATED_AGE);
    }

    @Test
    @Transactional
    public void updateNonExistingBird() throws Exception {
        int databaseSizeBeforeUpdate = birdRepository.findAll().size();

        // Create the Bird

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBirdMockMvc.perform(put("/api/birds")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bird)))
            .andExpect(status().isBadRequest());

        // Validate the Bird in the database
        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBird() throws Exception {
        // Initialize the database
        birdService.save(bird);

        int databaseSizeBeforeDelete = birdRepository.findAll().size();

        // Delete the bird
        restBirdMockMvc.perform(delete("/api/birds/{id}", bird.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bird> birdList = birdRepository.findAll();
        assertThat(birdList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
