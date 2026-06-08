package com.cliente.api.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @GetMapping
  public List<Account> getAllAccounts(){
    return accountService.getAllAccounts();
  }

  @GetMapping("/{accountId}")
  public Account getAccountById(@PathVariable Long accountId){
    return accountService.getAccountById(accountId);
  }

  @PostMapping
  public Account createAccount(@RequestBody Account account){ // Cambiado a @RequestBody
    return accountService.createAccount(account);
  }

  @PutMapping("/{accountId}")
  public Account updateAccount(@PathVariable Long accountId, @RequestBody Account account){
    account.setAccountId(accountId);
    return accountService.updateAccount(account);
  }

  @DeleteMapping("/{accountId}")
  public void deleteAccount(@PathVariable Long accountId){
    accountService.deleteAccount(accountId);
  }
}