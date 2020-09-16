package com.aurumTeste.service;

import com.aurumTeste.model.ClassificationTypeEnum;
import com.aurumTeste.model.Clipping;
import com.aurumTeste.repository.ClippingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClippingServiceTest {

    ClippingService clippingService;
    @MockBean
    private ClippingRepository repository;
    @MockBean
    private AppointmentService appointmentService;
    @MockBean
    private NotificationService notificationService;

    @BeforeEach
    public void setup() {
        clippingService = new ClippingService(repository);
    }

    @Test
    public void findById() {
        Clipping clipping = new Clipping();
        clipping.setViewed(false);
        clipping.setImportant(false);
        clipping.setClassificationType(ClassificationTypeEnum.DEADLINE);
        clippingService.save(clipping);
        when(repository.findById(1L)).thenReturn(Optional.of(clipping));
        assertTrue(clippingService.findById(1L).isPresent());
    }

    @Test
    public void save() {

    }
 }