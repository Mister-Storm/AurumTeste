package com.aurumTeste.event.listener;

import com.aurumTeste.event.ClippingCreatedEvent;
import com.aurumTeste.model.ClassificationTypeEnum;
import com.aurumTeste.model.Clipping;
import com.aurumTeste.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentListener implements ApplicationListener<ClippingCreatedEvent> {

    @Autowired
    AppointmentService appointmentService;

    @Override
    public void onApplicationEvent(ClippingCreatedEvent event) {
        if(event.getClassificationType() != null) {
            saveAppointment(event.getClipping());
        }

    }

    private void saveAppointment(Clipping clipping) {
        if(clipping.getClassifiedDate() != null) {
            appointmentService.saveForHearing(clipping.getClassifiedDate(), ClassificationTypeEnum.HEARING, false);
        } else {
            appointmentService.saveForHearing(clipping.getClippingDate(), ClassificationTypeEnum.HEARING, true);
        }
    }
}
