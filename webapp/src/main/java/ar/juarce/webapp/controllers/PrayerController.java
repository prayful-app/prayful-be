package ar.juarce.webapp.controllers;

import ar.juarce.interfaces.PrayerRequestService;
import ar.juarce.interfaces.PrayerService;
import ar.juarce.interfaces.UserService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.PrayerNotFoundException;
import ar.juarce.interfaces.exceptions.PrayerRequestNotFoundException;
import ar.juarce.models.Prayer;
import ar.juarce.models.PrayerRequest;
import ar.juarce.models.User;
import ar.juarce.webapp.dtos.PrayerDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

@Path("/prayers")
public class PrayerController {

    private final PrayerService prayerService;
    private final PrayerRequestService prayerRequestService;
    private final UserService userService;

    @Context
    private UriInfo uriInfo;

    @Autowired
    public PrayerController(PrayerService prayerService,
                            PrayerRequestService prayerRequestService,
                            UserService userService) {
        this.prayerService = prayerService;
        this.prayerRequestService = prayerRequestService;
        this.userService = userService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePrayer(@Valid PrayerDto prayerDto) throws PrayerRequestNotFoundException, AlreadyExistsException {
        final PrayerRequest prayerRequest = prayerRequestService.findById(prayerDto.prayerRequestId())
                .orElseThrow(() -> new PrayerRequestNotFoundException(prayerDto.prayerRequestId()));
        final User requester = userService.getCurrentUser()
                .orElseThrow(() -> new RuntimeException("User not logged in"));

        final Prayer prayer = prayerService.create(buildNewPrayer(prayerDto, requester, prayerRequest));

        return Response
                .created(uriInfo.getAbsolutePathBuilder()
                        .path(prayer.getId().toString())
                        .build())
                .entity(PrayerDto.fromPrayer(prayer))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrayer(@PathParam("id") Long id) throws PrayerNotFoundException {
        final Prayer prayer = prayerService.findById(id)
                .orElseThrow(() -> new PrayerNotFoundException(id));

        return Response
                .ok(PrayerDto.fromPrayer(prayer))
                .build();
    }

    /*
        Auxiliary methods
     */
    private Prayer buildNewPrayer(PrayerDto prayerDto, User believer, PrayerRequest prayerRequest) {
        return Prayer.builder()
                .prayerRequest(prayerRequest)
                .believer(believer)
                .content(prayerDto.content())
                .build();
    }
}
