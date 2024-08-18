package dk.BdCC.bankaccountsystem.models.datatransferobjects;

import dk.BdCC.bankaccountsystem.models.entities.BankAccountEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BankAccountResponseRequestDTO {
    public String accountNumber;
    public double balance;
    public String fName;
    public String lName;

    public BankAccountResponseRequestDTO(BankAccountEntity bA) {
        this.accountNumber = bA.accountNumber;
        this.balance = bA.balance;
        this.fName = bA.fName;
        this.lName = bA.lName;
    }
}
