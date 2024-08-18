package dk.BdCC.bankaccountsystem.models.entities;

import dk.BdCC.bankaccountsystem.models.datatransferobjects.BankAccountCreateRequestDTO;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
public class BankAccountEntity extends PanacheEntity {
    @Getter
    @Setter
    public double balance;
    public String accountNumber;
    public String fName;
    public String lName;

    public BankAccountEntity(BankAccountCreateRequestDTO request) {
        this.accountNumber = request.accountNumber;
        this.fName = request.fName;
        this.lName = request.lName;
    }
}
