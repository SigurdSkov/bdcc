package dk.BdCC.bankaccountsystem.repositories;

import dk.BdCC.bankaccountsystem.models.entities.BankAccountEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BankAccountRepository implements PanacheRepository<BankAccountEntity> {
}
