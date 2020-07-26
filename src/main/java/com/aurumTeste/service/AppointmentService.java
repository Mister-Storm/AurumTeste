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


    private final AppointmentRepository repository;

    @Autowired
    public AppointmentService(AppointmentRepository repository){
        this.repository = repository;
    }

    public Appointment saveForHearing(LocalDate clippingDate, ClassificationTypeEnum hearing, Boolean classifiedDateIsAbsent) {
        Appointment appointment = new Appointment();

        clippingDate = adjustClippingDataIfNecessary(clippingDate, classifiedDateIsAbsent);
        appointment.setDueDate(clippingDate);
        appointment.setDescription(hearing.toString());
        appointment.setCreatedAt(LocalDate.now());
        return repository.save(appointment);
    }

    private LocalDate adjustClippingDataIfNecessary(LocalDate clippingDate, Boolean classifiedDateIsAbsent) {
        if(classifiedDateIsAbsent) {
            sumDays(clippingDate);

            clippingDate = clippingDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) ? clippingDate.plusDays(2L) : clippingDate;
            clippingDate = clippingDate.getDayOfWeek().equals(DayOfWeek.SUNDAY) ? clippingDate.plusDays(1L) : clippingDate;
        }
        return clippingDate;
    }

    private void sumDays(LocalDate clippingDate) {
        if(clippingDate.getDayOfWeek().equals(DayOfWeek.FRIDAY) || clippingDate.getDayOfWeek().equals(DayOfWeek.THURSDAY) || clippingDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)) {
            clippingDate.plusDays(5L);
        } else {clippingDate.plusDays(3L);}
    }

    public Page<Appointment> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size);
        return repository.findAll(pageRequest);
    }
}
