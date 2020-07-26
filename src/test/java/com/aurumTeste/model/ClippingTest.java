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

class ClippingTest {

    private final String clippingDateMessage = "The field clippingDate is required. Please fill it.";
    private final String clippingMatterMessage = "The field clippingMatter is required. Please fill it.";

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Test
    public void shouldNotValidateWhenClippingDateIsNull() {
        Clipping clipping = new Clipping();
        clipping.setClippingDate(null);
        clipping.setClippingMatter("Any Matter");

        Set<ConstraintViolation<Clipping>> restrictions = validator.validate(clipping);

        assertThat(restrictions, hasSize(1));
        assertThat(restrictions.iterator().next().getMessage(), is(clippingDateMessage));

    }

    @Test
    public void shouldNotValidateWhenClippingMatterIsNull() {
        Clipping clipping = new Clipping();
        clipping.setClippingDate(LocalDate.now());
        clipping.setClippingMatter(null);
        Set<ConstraintViolation<Clipping>> restrictions = validator.validate(clipping);

        assertThat(restrictions, hasSize(1));
        assertThat(restrictions.iterator().next().getMessage(), is(clippingMatterMessage));

    }

    @Test
    public void shouldValidateWhenAllRequiredFieldsISFill() {
        Clipping clipping = new Clipping();
        clipping.setClippingDate(LocalDate.now());
        clipping.setClippingMatter("Any Matter");
        Set<ConstraintViolation<Clipping>> restrictions = validator.validate(clipping);

        assertThat(restrictions, hasSize(0));
    }

    @Test
    public void shouldNotValidateWhenDueDateAndDescriptionIsNull() {
        Clipping clipping = new Clipping();
        clipping.setClippingDate(null);
        clipping.setClippingMatter(null);
        Set<ConstraintViolation<Clipping>> restrictions = validator.validate(clipping);

        assertThat(restrictions, hasSize(2));
    }

}