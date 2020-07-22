package com.aurumTeste.service;

import com.aurumTeste.model.Notification;
import com.aurumTeste.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository repository;


    @Autowired
    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }


    public Notification saveForClipping(String messageToNotification) {
        Notification notification = new Notification();
        notification.setDescription(messageToNotification);
        notification.setViewed(false);
        return repository.save(notification);
    }

    public Page<Notification> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size);
        return repository.findAll(pageRequest);
    }
}
