package com.aurumTeste.ressources;

import com.aurumTeste.model.Appointment;
import com.aurumTeste.service.AppointmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/appointments")
@Api(tags = "Appointment controller")
public class AppointmentRessources {

    private final AppointmentService service;

    @Autowired
    public AppointmentRessources(AppointmentService service) {
        this.service = service;
    }

    @ApiOperation(value = "Return all appointments.")
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
