package dk.BdCC.bankaccountsystem.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import dk.BdCC.bankaccountsystem.models.entities.CounterEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CounterRepository implements PanacheRepository<CounterEntity> {

}
