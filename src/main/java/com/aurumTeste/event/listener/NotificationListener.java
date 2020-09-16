package com.aurumTeste.event.listener;

import com.aurumTeste.event.ClippingCreatedEvent;
import com.aurumTeste.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener implements ApplicationListener<ClippingCreatedEvent> {

    private static final String MESSAGE_TO_NOTIFICATION="You have a new important clipping";

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onApplicationEvent(ClippingCreatedEvent event) {
        if(event.getClassificationType() != null && event.getImportant()) {
            sendNotification();
        }
    }

    private void sendNotification() {
            notificationService.saveForClipping(MESSAGE_TO_NOTIFICATION);
    }
}
