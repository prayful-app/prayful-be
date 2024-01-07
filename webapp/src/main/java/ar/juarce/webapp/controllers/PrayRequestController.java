package ar.juarce.webapp.controllers;

import ar.juarce.interfaces.PrayRequestService;
import ar.juarce.interfaces.UserService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.models.PrayRequest;
import ar.juarce.models.User;
import ar.juarce.webapp.dtos.PrayRequestDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/pray-requests")
public class PrayRequestController {
    
    private final PrayRequestService prayRequestService;
    private final UserService userService;


    @Context
    private UriInfo uriInfo;

    @Autowired
    public PrayRequestController(PrayRequestService prayRequestService,
                                 UserService userService) {
        this.prayRequestService = prayRequestService;
        this.userService = userService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePrayRequest(@Valid PrayRequestDto prayRequestDto) throws AlreadyExistsException {
        final User requester = userService.getCurrentUser().orElseThrow(() -> new RuntimeException("User not logged in"));
        final PrayRequest prayRequest = prayRequestService.create(buildNewPrayRequest(prayRequestDto, requester));

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(prayRequest.getId().toString())
                        .build())
                .entity(PrayRequestDto.fromPrayRequest(prayRequest))
                .build();
    }

    private PrayRequest buildNewPrayRequest(PrayRequestDto prayRequestDto, User requester) {
        return PrayRequest.builder()
                .requester(requester)
                .request(prayRequestDto.request())
                .build();
    }
}
