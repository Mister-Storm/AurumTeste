package com.aurumTeste.service;

import com.aurumTeste.model.ClassificationTypeEnum;
import com.aurumTeste.model.Clipping;
import com.aurumTeste.repository.ClippingRepository;
import com.aurumTeste.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClippingService {

	private ClippingRepository repository;
	private AppointmentService appointmentService;
	private NotificationService notificationService;

	private static final String MESSAGE_TO_NOTIFICATION="You have a new important clipping";

	public ClippingService(){}
	
	@Autowired
	public ClippingService(ClippingRepository repository, AppointmentService appointmentService, NotificationService notificationService) {
		this.repository = repository;
		this.appointmentService = appointmentService;
		this.notificationService = notificationService;
	}
	
	public Optional<Clipping> findById(Long id) {
		Optional<Clipping> optional = repository.findById(id);
		if (optional.isPresent()) {
			Clipping clipping = optional.get();
			clipping.setViewed(true);
			this.save(clipping);
		}
		return optional;
	}

	public Page<Clipping> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(
				page,
				size);
		return repository.findAll(pageRequest);
	}

	
	public Clipping save(Clipping clipping) {
		if(clipping.getClassificationType().equals(ClassificationTypeEnum.HEARING)) {
			saveAppointmentIfNecessary(clipping);
			saveNotificationIfNecessary(clipping);

		}
		return repository.save(clipping);
	}

	private void saveNotificationIfNecessary(Clipping clipping) {
		if(clipping.getImportant()==true) {
			notificationService.saveForClipping(MESSAGE_TO_NOTIFICATION);

		}
	}

	private void saveAppointmentIfNecessary(Clipping clipping) {
		if(clipping.getClassifiedDate() != null) {
			appointmentService.saveForHearing(clipping.getClassifiedDate(), ClassificationTypeEnum.HEARING, false);
		} else {
			appointmentService.saveForHearing(clipping.getClippingDate(), ClassificationTypeEnum.HEARING, true);
		}
	}

	public Clipping markeAsRead(Long id, Boolean viewed) {
		Optional<Clipping> result = this.findById(id);
		Clipping clipping = result.orElseThrow(ObjectNotFoundException::new);
		clipping.setViewed(viewed);
		return this.save(clipping);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public void delete() {
		repository.deleteAll();
	}

}
