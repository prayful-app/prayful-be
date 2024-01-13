package ar.juarce.interfaces.exceptions;

public class PrayerNotFoundException extends NotFoundException {

    public PrayerNotFoundException(Long id) {
        super(String.format("Prayer with id %s", id));
    }
}
