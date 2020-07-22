package com.aurumTeste.service;

import com.aurumTeste.model.Clipping;
import com.aurumTeste.repository.ClippingRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@DataJpaTest
class ClippingServiceTest {



    @TestConfiguration
    static class ClippingServiceTestContextConfiguration{
        @Bean
        public ClippingService clippingService(){
            return new ClippingService();
        }
    }

    @Autowired
    ClippingService service;

    @MockBean
    ClippingRepository repository;

    @Test
    void findById() {
        Clipping c = new Clipping();
        c.setViewed(true);
        c.setId(1L);
        Mockito.doReturn(c).when(service).findById(1L);
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void markeAsRead() {
    }

    @Test
    void delete() {
    }
}