package ar.juarce.services;

import ar.juarce.interfaces.PrayerDao;
import ar.juarce.interfaces.PrayerService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.Prayer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerServiceImpl implements PrayerService {

    private final PrayerDao prayerDao;

    @Autowired
    public PrayerServiceImpl(PrayerDao prayerDao) {
        this.prayerDao = prayerDao;
    }

    @Transactional
    @Override
    public Prayer create(Prayer entity) throws AlreadyExistsException {
        return prayerDao.create(entity);
    }

    @Override
    public Optional<Prayer> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Prayer> findAll(Filter<Prayer> filter) {
        return null;
    }

    @Override
    public Prayer update(Long aLong, Prayer entity) throws NotFoundException, AlreadyExistsException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Prayer entity) {

    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
