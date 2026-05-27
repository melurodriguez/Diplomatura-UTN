package com.cliente.api.account;

import com.cliente.api.client.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
  @Autowired
  private AccountRepository accountRepository;

  public List<Account> getAllAccounts(){
    return accountRepository.findAll();
  }

  public Account getAccountById(Long id){
    return accountRepository.getReferenceById(id);
  }

  public Account createAccount(Account account){
    return accountRepository.save(account);
  }

  public Account updateAccount(Account account){
    return accountRepository.save(account);
  }

  public void deleteAccount(Long id){
    accountRepository.deleteById(id);
  }
}
