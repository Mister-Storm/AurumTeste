package com.aurumTeste.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class AppointmentTest {

    private final String dueDateMessage = "The field dueDate is required. Please fill it.";
    private final String descriptionMessage = "The field description is required. Please fill it.";

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void shouldNotValidateWhenDueDateIsNull() {
        Appointment appointment = new Appointment();
        appointment.setDueDate(null);
        appointment.setDescription("Any description");
        Set<ConstraintViolation<Appointment>> restrictions = validator.validate(appointment);

        assertThat(restrictions, hasSize(1));
        assertThat(restrictions.iterator().next().getMessage(), is(dueDateMessage));

    }

    @Test
    public void shouldNotValidateWhenDescriptionIsNull() {
        Appointment appointment = new Appointment();
        appointment.setDueDate(LocalDate.now());
        appointment.setDescription(null);
        Set<ConstraintViolation<Appointment>> restrictions = validator.validate(appointment);

        assertThat(restrictions, hasSize(1));
        assertThat(restrictions.iterator().next().getMessage(), is(descriptionMessage));

    }

    @Test
    public void shouldValidateWhenAllRequiredFieldsISFill() {
        Appointment appointment = new Appointment();
        appointment.setDueDate(LocalDate.now());
        appointment.setDescription("Any description");
        Set<ConstraintViolation<Appointment>> restrictions = validator.validate(appointment);

        assertThat(restrictions, hasSize(0));
    }

    @Test
    public void shouldNotValidateWhenDueDateAndDescriptionIsNull() {
        Appointment appointment = new Appointment();
        appointment.setDueDate(null);
        appointment.setDescription(null);
        Set<ConstraintViolation<Appointment>> restrictions = validator.validate(appointment);

        assertThat(restrictions, hasSize(2));
    }

}