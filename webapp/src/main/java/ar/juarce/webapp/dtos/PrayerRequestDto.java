package ar.juarce.webapp.dtos;

import ar.juarce.models.PrayerRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PrayerRequestDto(
        Long id,

        UserDto requester,

        @NotNull()
        @Size(min = 1, max = 1024)
        String request,

        String createdAt
) {

    public static PrayerRequestDto fromPrayRequest(PrayerRequest prayerRequest) {
        return new PrayerRequestDto(
                prayerRequest.getId(),
                UserDto.fromUser(prayerRequest.getRequester()),
                prayerRequest.getRequest(),
                prayerRequest.getCreatedAt().toString()
        );
    }

    public static List<PrayerRequestDto> fromPrayRequests(List<PrayerRequest> prayerRequests) {
        return prayerRequests.stream().map(PrayerRequestDto::fromPrayRequest).toList();
    }
}
