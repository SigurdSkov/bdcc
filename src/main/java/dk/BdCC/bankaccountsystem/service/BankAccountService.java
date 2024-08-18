package dk.BdCC.bankaccountsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.BdCC.bankaccountsystem.models.conversions.CurrencyConversionLatestDTO;
import dk.BdCC.bankaccountsystem.models.datatransferobjects.*;
import dk.BdCC.bankaccountsystem.models.entities.BankAccountEntity;
import dk.BdCC.bankaccountsystem.repositories.BankAccountRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import dk.BdCC.bankaccountsystem.models.datatransferobjects.BankAccountResponseRequestDTO;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class BankAccountService {
    @Inject
    BankAccountRepository bankAccountRepository;
    HttpClient httpClient = HttpClient.newHttpClient();
    String baseUrl = "https://v6.exchangerate-api.com/v6";
    ObjectMapper mapper = new ObjectMapper();

    @Transactional
    public BankAccountResponseRequestDTO createBankAccount(BankAccountCreateRequestDTO bARqD) {
        var newBankAccount = new BankAccountEntity(bARqD);
        bankAccountRepository.persist(newBankAccount);
        return new BankAccountResponseRequestDTO(newBankAccount);
    }

    @Transactional
    public BankAccountResponseRequestDTO addBalance(BankAccountDepositRequestDTO balanceRequestDTO) {
        var account = findAccountByIdLocal(balanceRequestDTO.getId());
        account.setBalance(account.getBalance() + balanceRequestDTO.getIncomingMoney());
        bankAccountRepository.persist(account);
        return new BankAccountResponseRequestDTO(account);
    }

    @Transactional
    public void setSenderTransaction(BankAccountEntity sender, double amount) {
        sender.setBalance(sender.getBalance() - amount);
        bankAccountRepository.persist(sender);
    }

    @Transactional
    public void setReceiverTransaction(BankAccountEntity receiver, double amount) {
        receiver.setBalance(receiver.getBalance() + amount);
        bankAccountRepository.persist(receiver);
    }

    @Transactional
    public List<BankAccountResponseRequestDTO> transferBalance(BankAccountSenderRequestDTO accountSenderDTO, BankAccountReceiveRequestDTO accountReceiverDTO) {
        var bankAccountSender = findAccountByIdLocal(accountSenderDTO.getId());
        var bankAccountReceiver = findAccountByIdLocal(accountReceiverDTO.getId());
        if (accountSenderDTO.getIncomingMoney() >= 0) {
            //Sender
            setSenderTransaction(bankAccountSender, accountSenderDTO.getIncomingMoney());
            //Modtager
            setReceiverTransaction(bankAccountReceiver, accountSenderDTO.getIncomingMoney());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<BankAccountResponseRequestDTO> responses = new ArrayList<>();
        responses.add(new BankAccountResponseRequestDTO(bankAccountSender));
        responses.add(new BankAccountResponseRequestDTO(bankAccountReceiver));
        return responses;
    }

    public BankAccountEntity findAccountByIdLocal(long id) {
        var bankAccount = bankAccountRepository.findById(id);
        if (bankAccount != null)
            return bankAccount;
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public BankAccountResponseRequestDTO findAccountById(long id) {
        var bankAccount = bankAccountRepository.findById(id);
        if (bankAccount != null)
            return new BankAccountResponseRequestDTO(bankAccount);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public CurrencyConversionLatestDTO converterWorker(String endpoint) {
        URI uri;
        try {
            uri = new URI(endpoint);
        } catch (URISyntaxException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        var req = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        HttpResponse<String> resp;
        try {
            resp = httpClient.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (resp.statusCode() != 200) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CurrencyConversionLatestDTO latestDto;
        try {
            latestDto = mapper.readValue(resp.body(), CurrencyConversionLatestDTO.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return latestDto;
    }

    public CurrencyConversionLatestDTO historicalAndCurrentConversionRate(LocalDate date) {
        final String apiKey = "0f1466a027462f366009d409";
        String endpoint = String.format("%s/%s/latest/USD", baseUrl, apiKey);
        if (LocalDate.now(ZoneId.of("Europe/Copenhagen")).equals(date))
            return converterWorker(endpoint);
        if (date.getYear() < 2005 || date.getYear() > 2015 || date.getYear() == 2012) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String historicEndpoint = String.format("%s/%s/history/USD/%d/%d/%d", baseUrl, apiKey, date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        return converterWorker(historicEndpoint);
    }
}