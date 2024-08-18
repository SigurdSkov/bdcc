package dk.BdCC.bankaccountsystem.models.entities;

import dk.BdCC.bankaccountsystem.models.CounterEntityRequestDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CounterEntity extends PanacheEntity {
    public long count;
    public String name;

    public CounterEntity(CounterEntity ce) {
        this.count = ce.count;
        this.name = ce.name;
    }

    public CounterEntity(CounterEntityRequestDTO ce) {
        this.name = ce.name;
        this.count = 0;
    }
}
