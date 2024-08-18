package dk.BdCC.bankaccountsystem.models.datatransferobjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccountTransferBalanceRequestDTO {
    private BankAccountSenderRequestDTO sender;
    private BankAccountReceiveRequestDTO receiver;
}
