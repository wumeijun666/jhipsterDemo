package com.mycompany.myapp.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.mycompany.myapp.web.rest.TestUtil;

public class BirdTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bird.class);
        Bird bird1 = new Bird();
        bird1.setId(1L);
        Bird bird2 = new Bird();
        bird2.setId(bird1.getId());
        assertThat(bird1).isEqualTo(bird2);
        bird2.setId(2L);
        assertThat(bird1).isNotEqualTo(bird2);
        bird1.setId(null);
        assertThat(bird1).isNotEqualTo(bird2);
    }
}
