package ar.juarce.services;

import ar.juarce.interfaces.PrayRequestDao;
import ar.juarce.interfaces.PrayRequestService;
import ar.juarce.interfaces.exceptions.AlreadyExistsException;
import ar.juarce.interfaces.exceptions.NotFoundException;
import ar.juarce.models.PrayRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrayRequestServiceImpl implements PrayRequestService {

    private final PrayRequestDao prayRequestDao;

    @Autowired
    public PrayRequestServiceImpl(PrayRequestDao prayRequestDao) {
        this.prayRequestDao = prayRequestDao;
    }

    @Transactional
    @Override
    public PrayRequest create(PrayRequest entity) throws AlreadyExistsException {
        return prayRequestDao.create(entity);
    }

    @Override
    public Optional<PrayRequest> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<PrayRequest> findAll() {
        return null;
    }

    @Override
    public PrayRequest update(Long aLong, PrayRequest entity) throws NotFoundException, AlreadyExistsException {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(PrayRequest entity) {

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
