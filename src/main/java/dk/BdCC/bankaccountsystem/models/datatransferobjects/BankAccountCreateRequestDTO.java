package dk.BdCC.bankaccountsystem.models.datatransferobjects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BankAccountCreateRequestDTO {
    public String accountNumber;
    public String fName;
    public String lName;
}
