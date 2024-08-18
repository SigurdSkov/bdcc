package dk.BdCC.bankaccountsystem.models;

import dk.BdCC.bankaccountsystem.models.entities.CounterEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CounterEntityResponseDTO {
    public long id;
    public String name;
    public long count;

    public CounterEntityResponseDTO(CounterEntity ce) {
        this.id = ce.id;
        this.count = ce.count;
        this.name = ce.name;
    }
}
