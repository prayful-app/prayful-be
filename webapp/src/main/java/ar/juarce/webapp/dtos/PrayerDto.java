package ar.juarce.webapp.dtos;

import ar.juarce.models.Prayer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record PrayerDto(
        Long id,

        @NotNull()
        Long prayerRequestId,

        PrayerRequestDto prayerRequest,

        UserDto believer,

        @NotNull()
        @Size(max = 1024)
        String content,

        String createdAt
) {

    public static PrayerDto fromPrayer(Prayer prayer) {
        return new PrayerDto(
                prayer.getId(),
                prayer.getPrayerRequest().getId(),
                PrayerRequestDto.fromPrayRequest(prayer.getPrayerRequest()),
                UserDto.fromUser(prayer.getBeliever()),
                prayer.getContent(),
                prayer.getCreatedAt().toString()
        );
    }

    public static List<PrayerDto> fromPrayers(List<Prayer> prayers) {
        return prayers.stream().map(PrayerDto::fromPrayer).toList();
    }
}
