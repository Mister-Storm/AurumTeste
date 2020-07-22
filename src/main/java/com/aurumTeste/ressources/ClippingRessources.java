package com.aurumTeste.ressources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurumTeste.model.Clipping;
import com.aurumTeste.service.ClippingService;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/v1/clippings")
public class ClippingRessources {

	private final ClippingService service;
	
	@Autowired
	public ClippingRessources(ClippingService service) {
	this.service=service;
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Clipping> clipping = service.findById(id);
		return clipping.isPresent() ? new  ResponseEntity<>(service.findById(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping
	public Page<Clipping> findAll(@RequestParam(
			value = "page",
			required = false,
			defaultValue = "0") int page,
						   @RequestParam(
								   value = "size",
								   required = false,
								   defaultValue = "10") int size) {
		return service.findAll(page, size);
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Clipping clipping) {

		return new ResponseEntity<>(service.save(clipping), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> markeAsRead(@PathVariable Long id, Boolean viewed) {
		return new ResponseEntity<>(service.markeAsRead(id, viewed), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
@DeleteMapping
	public ResponseEntity<?> delete() {
		service.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
