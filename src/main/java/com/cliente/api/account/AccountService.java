package com.cliente.api.account;

import com.cliente.api.client.ClientService;
import com.cliente.api.client.Cliente;
import com.cliente.api.dolar.DolarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private ClientService clientService;
  @Autowired
  private DolarClient dolarClient;

  public List<Account> getAllAccounts(){
    return accountRepository.findAll();
  }

  public Account getAccountById(Long id){
    return accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException("No se encontró la cuenta con ID: " + id));
  }

  public Account createAccount(Account account){
    if (account.getCliente() == null || account.getCliente().getClientId() == null) {
      throw new IllegalArgumentException("La cuenta debe estar asociada a un cliente.");
    }
    Long idCliente = account.getCliente().getClientId();
    Cliente clienteExistente =clientService.getClientById(idCliente);

    account.setCliente(clienteExistente);
    account.setCreatedAt(LocalDate.now());

    return accountRepository.save(account);
  }

  public Account updateAccount(Account account){
    getAccountById(account.getAccountId());
    return accountRepository.save(account);
  }

  public void deleteAccount(Long id){
    getAccountById(id);
    accountRepository.deleteById(id);
  }

  public void changeCurrencyType(Account account, Currency newCurrency){
    if(account.getCurrency().equals(Currency.USD) && newCurrency.equals(Currency.ARS)){
      dolarClient.getDolar("blue");
      account.setCurrency(newCurrency);
    }
  }
}