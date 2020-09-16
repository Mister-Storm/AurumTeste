package com.aurumTeste.service;

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
	private static final String MESSAGE_TO_NOTIFICATION="You have a new important clipping";

	@Autowired
	public ClippingService(ClippingRepository repository) {
		this.repository = repository;
	}
	
	public Optional<Clipping> findById(Long id) {
		Optional<Clipping> optional = repository.findById(id);
			Clipping clipping = optional.orElseThrow(ObjectNotFoundException::new);;
			clipping.setViewed(true);
			this.save(clipping);
		return Optional.of(clipping);
	}

	public Page<Clipping> findAll(int page, int size) {
		PageRequest pageRequest = PageRequest.of(
				page,
				size);
		return repository.findAll(pageRequest);
	}

	
	public Clipping save(Clipping clipping) {
		clipping.setViewed(clipping.getId() != null ? true : false);
		return repository.save(clipping);
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
