package com.aurumTeste.ressources;

import com.aurumTeste.event.ClippingCreatedEvent;
import com.aurumTeste.model.Clipping;
import com.aurumTeste.service.ClippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/v1/clippings")
@Api(tags = "Clipping Controller")
@ApiResponses(value = {
		@ApiResponse(code = 200,
				message = ResponseMessages.OK_200),
		@ApiResponse(code = 201,
				message = ResponseMessages.CREATED_201),
		@ApiResponse(code = 400,
				message = ResponseMessages.BAD_REQUEST_400),
		@ApiResponse(code = 401,
				message = ResponseMessages.UNAUTHORIZED_401),
		@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
})
public class ClippingRessources {

	private final ClippingService service;

	private final ApplicationEventPublisher publisher;


	@Autowired
	public ClippingRessources(ClippingService service, ApplicationEventPublisher publisher) {
		this.service=service;
		this.publisher = publisher;
	}

	@ApiOperation(value = "return a clipping for id.")

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Clipping> clipping = service.findById(id);
		return clipping.isPresent() ? new  ResponseEntity<>(service.findById(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "return all clippings.")
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

	@ApiOperation(value = "save a clipping.")
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Clipping clipping) {

		publisher.publishEvent(new ClippingCreatedEvent(this, clipping));
		return new ResponseEntity<>(service.save(clipping), HttpStatus.CREATED);
	}

	@ApiOperation(value = "modify a clipping for id.")
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> markeAsRead(@PathVariable Long id, Boolean viewed) {
		return new ResponseEntity<>(service.markeAsRead(id, viewed), HttpStatus.OK);
	}

	@ApiOperation(value = "delete a clipping for id.")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "delete all clippings.")
	@DeleteMapping
	public ResponseEntity<?> delete() {
		service.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
