package dk.BdCC.bankaccountsystem.service;

import dk.BdCC.bankaccountsystem.models.CounterEntityRequestDTO;
import dk.BdCC.bankaccountsystem.models.CounterEntityResponseDTO;
import dk.BdCC.bankaccountsystem.models.entities.CounterEntity;
import dk.BdCC.bankaccountsystem.repositories.CounterRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class CounterService {
    @Inject
    CounterRepository counterRepository;
    @Inject
    EntityManager entityManager;

    public Set<CounterEntity> findAll() {
        return new HashSet<>(counterRepository.listAll()); //Alternativ til list->stream->collection
    }

    public CounterEntity findCount(String id) {
        long tId = Long.parseLong(id);
        var counter = counterRepository.findById(tId);
        if (counter != null) {
            return counter;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public CounterEntityResponseDTO createCounter(CounterEntityRequestDTO ce) {
        var newCount = new CounterEntity(ce);
        counterRepository.persist(newCount);
        return new CounterEntityResponseDTO(newCount);
    }
}
/*
    @Transactional
    public CounterEntity save(CounterEntity counter) {
        counterRepository.persist(counter);
        return counter;
    }

    public CounterEntity findById(Long id) {
        return counterRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        counterRepository.deleteById(id);
    }*/


    /*
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void persist(dk.BdCC.BankAccountSystem.Entities.CounterEntity counter) {
        em.persist(counter);
    }
    */