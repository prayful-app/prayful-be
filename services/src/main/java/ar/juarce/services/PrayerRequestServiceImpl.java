package ar.juarce.services;

import ar.juarce.interfaces.PrayerRequestDao;
import ar.juarce.interfaces.PrayerRequestService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.PrayerRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerRequestServiceImpl implements PrayerRequestService {

    private final PrayerRequestDao prayerRequestDao;

    @Autowired
    public PrayerRequestServiceImpl(PrayerRequestDao prayerRequestDao) {
        this.prayerRequestDao = prayerRequestDao;
    }

    @Transactional
    @Override
    public PrayerRequest create(PrayerRequest entity) throws AlreadyExistsException {
        return prayerRequestDao.create(entity);
    }

    @Override
    public Optional<PrayerRequest> findById(Long id) {
        return prayerRequestDao.findById(id);
    }

    @Override
    public List<PrayerRequest> findAll(Filter<PrayerRequest> filter) {
        return prayerRequestDao.findAll(filter);
    }

    @Override
    public PrayerRequest update(Long aLong, PrayerRequest entity) throws NotFoundException, AlreadyExistsException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PrayerRequest entity) {

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
