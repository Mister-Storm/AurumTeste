package com.aurumTeste.event;

import com.aurumTeste.model.ClassificationTypeEnum;
import com.aurumTeste.model.Clipping;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDate;

public class ClippingCreatedEvent extends ApplicationEvent {

    private Clipping clipping;

    public ClippingCreatedEvent(Object source, Clipping clipping) {
        super(source);
        this.clipping=clipping;
    }

    public Clipping getClipping() {
        return clipping;
    }

    public void setClipping(Clipping clipping) {
        this.clipping = clipping;
    }

    public Boolean getImportant() {
        return clipping.getImportant();
    }

    public ClassificationTypeEnum getClassificationType() {
        return clipping.getClassificationType();
    }

    public LocalDate getClassifiedDate() {
        return clipping.getClassifiedDate();
    }
}

