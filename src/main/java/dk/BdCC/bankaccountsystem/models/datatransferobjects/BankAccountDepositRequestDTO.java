package dk.BdCC.bankaccountsystem.models.datatransferobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountDepositRequestDTO {
    long id;
    double incomingMoney;
}
