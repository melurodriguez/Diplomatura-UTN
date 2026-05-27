package com.cliente.api.account;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private AccountService accountService;

  @GetMapping("/")
  public List<Account> getAllAccounts(){
    return accountService.getAllAccounts();
  }

  @GetMapping("/{accountId}")
  public Account getAccountById(@PathVariable Long accountId){
    return accountService.getAccountById(accountId);
  }

  @PostMapping("/{account}")
  public Account creteAccount(@PathVariable Account account){
    return accountService.createAccount(account);
  }

  @PutMapping("/{accountId}")
  public Account updateAccount(@PathVariable Long accountId, Account account){
    return accountService.updateAccount(account);
  }

  @DeleteMapping("/{accountId}")
  public void deleteAccount(@PathVariable Long accountId){
    accountService.deleteAccount(accountId);
  }
}
