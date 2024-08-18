package dk.BdCC.bankaccountsystem.controllers;

import dk.BdCC.bankaccountsystem.models.conversions.CurrencyConversionLatestDTO;
import dk.BdCC.bankaccountsystem.models.datatransferobjects.*;
import dk.BdCC.bankaccountsystem.service.BankAccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.List;

@Path("/api/bankaccount")
public class BankAccountController {
    @Inject
    BankAccountService bankAccountService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BankAccountResponseRequestDTO createBankAccount(BankAccountCreateRequestDTO bARqD) {
        return bankAccountService.createBankAccount(bARqD);
    }

    @PUT
    @Path("/addbalance")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public BankAccountResponseRequestDTO addBalance(BankAccountDepositRequestDTO balanceRequestDTO) {
        return bankAccountService.addBalance(balanceRequestDTO);
    }

    @PUT
    @Path("/transferbalance")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<BankAccountResponseRequestDTO> transferBalance(BankAccountTransferBalanceRequestDTO transferBalanceRequestDTO) {// BankAccountBalanceRequestDTO accountSender, BankAccountReceiveRequestDTO accountReceiver) {
        return bankAccountService.transferBalance(transferBalanceRequestDTO.getSender(), transferBalanceRequestDTO.getReceiver()); //Set chosen, because why would you send money from one account to the same account?
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BankAccountResponseRequestDTO getAccount(@PathParam("id") long id) {
        return bankAccountService.findAccountById(id);
    }

    @GET
    @Path("/conversions/{date}")
    @Produces(MediaType.APPLICATION_JSON)
    public CurrencyConversionLatestDTO conversionRate(@PathParam("date") LocalDate date) {
        return bankAccountService.historicalAndCurrentConversionRate(date);
    }
}
