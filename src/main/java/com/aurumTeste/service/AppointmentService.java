package com.aurumTeste.service;

import com.aurumTeste.model.Appointment;
import com.aurumTeste.model.ClassificationTypeEnum;
import com.aurumTeste.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository){
        this.repository = repository;
    }

    public Appointment saveForHearing(LocalDate clippingDate, ClassificationTypeEnum hearing, Boolean classifiedDateIsAbsent) {
        Appointment appointment = new Appointment();

        clippingDate = adjustClippingDataIfNecessary(clippingDate, classifiedDateIsAbsent);
        appointment.setDueDate(clippingDate);
        appointment.setDescription(hearing.toString());
        return repository.save(appointment);
    }

    private LocalDate adjustClippingDataIfNecessary(LocalDate clippingDate, Boolean classifiedDateIsAbsent) {
        if(classifiedDateIsAbsent) {
            clippingDate.plusDays(3L);
            clippingDate = clippingDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ? clippingDate.plusDays(2L) : clippingDate;
            clippingDate = clippingDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) ? clippingDate.plusDays(1L) : clippingDate;
        }
        return clippingDate;
    }

    public Page<Appointment> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size);
        return repository.findAll(pageRequest);
    }
}
