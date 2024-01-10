package ar.juarce.interfaces.exceptions;

public class PrayerRequestNotFoundException extends NotFoundException {

    public PrayerRequestNotFoundException(Long id) {
        super(String.format("Prayer request with id %s", id));
    }
}
