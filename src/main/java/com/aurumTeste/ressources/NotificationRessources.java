package com.aurumTeste.ressources;

import com.aurumTeste.model.Notification;
import com.aurumTeste.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/v1/notifications")
public class NotificationRessources {

    private final NotificationService service;

    @Autowired
    public NotificationRessources(NotificationService service) {
        this.service=service;
    }

    @GetMapping
    public Page<Notification> findAll(@RequestParam(
            value = "page",
            required = false,
            defaultValue = "0") int page,
                                      @RequestParam(
                                          value = "size",
                                          required = false,
                                          defaultValue = "10") int size) {
        return service.findAll(page, size);
    }

}

