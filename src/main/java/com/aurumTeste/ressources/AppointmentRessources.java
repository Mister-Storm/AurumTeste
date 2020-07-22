package com.aurumTeste.ressources;

import com.aurumTeste.model.Appointment;
import com.aurumTeste.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/appointments")
public class AppointmentRessources {

    private final AppointmentService service;

    @Autowired
    public AppointmentRessources(AppointmentService service) {
        this.service = service;
    }

    public Page<Appointment> findAll(@RequestParam(
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
