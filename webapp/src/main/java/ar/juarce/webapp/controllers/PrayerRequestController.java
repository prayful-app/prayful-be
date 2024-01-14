package ar.juarce.webapp.controllers;

import ar.juarce.interfaces.PrayerRequestService;
import ar.juarce.interfaces.UserService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.PrayerRequestNotFoundException;
import ar.juarce.models.PrayerRequest;
import ar.juarce.models.PrayerRequestFilter;
import ar.juarce.models.User;
import ar.juarce.webapp.dtos.PrayerRequestDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Path("/prayer-requests")
public class PrayerRequestController {

    private final PrayerRequestService prayerRequestService;
    private final UserService userService;


    @Context
    private UriInfo uriInfo;

    @Autowired
    public PrayerRequestController(PrayerRequestService prayerRequestService,
                                   UserService userService) {
        this.prayerRequestService = prayerRequestService;
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrayerRequests(@QueryParam("requester-id") Long requesterId,
                                      @QueryParam("recommended-for-id") Long recommendedForId) {
        final List<PrayerRequest> prayerRequests = prayerRequestService.findAll(buildPrayerRequestFilter(requesterId, recommendedForId));

        if (prayerRequests.isEmpty()) {
            return Response
                    .noContent()
                    .build();
        }

        return Response
                .ok(PrayerRequestDto.fromPrayRequests(prayerRequests))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePrayRequest(@Valid PrayerRequestDto prayerRequestDto) throws AlreadyExistsException {
        final User requester = userService.getCurrentUser().orElseThrow(() -> new RuntimeException("User not logged in"));
        final PrayerRequest prayerRequest = prayerRequestService.create(buildNewPrayerRequest(prayerRequestDto, requester));

        return Response
                .created(uriInfo
                        .getAbsolutePathBuilder()
                        .path(prayerRequest.getId().toString())
                        .build())
                .entity(PrayerRequestDto.fromPrayRequest(prayerRequest))
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrayRequest(@PathParam("id") Long id) throws PrayerRequestNotFoundException {
        final PrayerRequest prayerRequest = prayerRequestService
                .findById(id)
                .orElseThrow(() -> new PrayerRequestNotFoundException(id));

        return Response
                .ok(PrayerRequestDto.fromPrayRequest(prayerRequest))
                .build();
    }


    /*
        Auxiliary methods
     */
    private PrayerRequest buildNewPrayerRequest(PrayerRequestDto prayerRequestDto, User requester) {
        return PrayerRequest.builder()
                .requester(requester)
                .request(prayerRequestDto.request())
                .build();
    }

    private PrayerRequestFilter buildPrayerRequestFilter(Long requesterId, Long recommendedForId) {
        return PrayerRequestFilter.builder()
                .requesterId(requesterId)
                .recommendedForId(recommendedForId)
                .build();
    }
}
