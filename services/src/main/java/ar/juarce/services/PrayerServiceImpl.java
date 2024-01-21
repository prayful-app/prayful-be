package ar.juarce.services;

import ar.juarce.interfaces.PrayerDao;
import ar.juarce.interfaces.PrayerService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.Filter;
import ar.juarce.models.Prayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PrayerServiceImpl implements PrayerService {

    private final PrayerDao prayerDao;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public PrayerServiceImpl(PrayerDao prayerDao,
                             ApplicationEventPublisher publisher) {
        this.prayerDao = prayerDao;
        this.publisher = publisher;
    }

    @Transactional
    @Override
    public Prayer create(Prayer entity) throws AlreadyExistsException {
        if (entity.getPrayerRequest().getRequester().equals(entity.getBeliever())) {
            throw new RuntimeException("Users can't pray for themselves"); // TODO create custom exception
        }
        final Prayer prayer = prayerDao.create(entity);
        publisher.publishEvent(prayer);
        return prayer;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Prayer> findById(Long id) {
        return prayerDao.findById(id);
    }

    @Override
    public List<Prayer> findAll(Filter<Prayer> filter) {
        return prayerDao.findAll(filter);
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
