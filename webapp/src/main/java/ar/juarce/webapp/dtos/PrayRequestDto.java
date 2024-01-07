package ar.juarce.webapp.dtos;

import ar.juarce.models.PrayRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PrayRequestDto(
        Long id,

        UserDto requester,

        @NotNull()
        @Size(min = 1, max = 1024)
        String request,

        String createdAt
) {

    public static PrayRequestDto fromPrayRequest(PrayRequest prayRequest) {
        return new PrayRequestDto(
                prayRequest.getId(),
                UserDto.fromUser(prayRequest.getRequester()),
                prayRequest.getRequest(),
                prayRequest.getCreatedAt().toString()
        );
    }

    public static List<PrayRequestDto> fromPrayRequests(List<PrayRequest> prayRequests) {
        return prayRequests.stream().map(PrayRequestDto::fromPrayRequest).toList();
    }
}
