package com.aurumTeste.ressources;

import com.aurumTeste.model.Clipping;
import com.aurumTeste.service.ClippingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="/v1/clippings")
@Api(tags = "Clipping Controller")
public class ClippingRessources {

	private final ClippingService service;
	
	@Autowired
	public ClippingRessources(ClippingService service) {
	this.service=service;
	}

	@ApiOperation(value = "return a clipping for id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Clipping> clipping = service.findById(id);
		return clipping.isPresent() ? new  ResponseEntity<>(service.findById(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "return all clippings.")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
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
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Clipping clipping) {

		return new ResponseEntity<>(service.save(clipping), HttpStatus.CREATED);
	}

	@ApiOperation(value = "modify a clipping for id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
	@PatchMapping(value = "/{id}")
	public ResponseEntity<?> markeAsRead(@PathVariable Long id, Boolean viewed) {
		return new ResponseEntity<>(service.markeAsRead(id, viewed), HttpStatus.OK);
	}

	@ApiOperation(value = "delete a clipping for id.")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation(value = "delete all clippings.")
	@ApiResponses(value = {
			@ApiResponse(code = 200,
					message = ResponseMessages.OK_200),
			@ApiResponse(code = 400,
					message = ResponseMessages.BAD_REQUEST_400),
			@ApiResponse(code = 401,
					message = ResponseMessages.UNAUTHORIZED_401),
			@ApiResponse(code = 404, message = ResponseMessages.NOT_FOUND_404)
	})
	@DeleteMapping
	public ResponseEntity<?> delete() {
		service.delete();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
