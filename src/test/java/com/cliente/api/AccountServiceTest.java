package com.cliente.api;

import com.cliente.api.dolar.Dolar;
import com.cliente.api.account.Account;
import com.cliente.api.account.AccountRepository;
import com.cliente.api.account.AccountService;
import com.cliente.api.account.Currency;
import com.cliente.api.client.ClientService;
import com.cliente.api.client.ClientePersonaFisica;
import com.cliente.api.dolar.DolarApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

  @Mock
  private AccountRepository accountRepository;
  @Mock
  private ClientService clientService;
  @Mock
  private DolarApiService dolarApiService;

  @InjectMocks
  private AccountService accountService;



  private Account accountEjemplo;
  private ClientePersonaFisica clientePropietario;

  @BeforeEach
  void setUp(){
    clientePropietario = ClientePersonaFisica.builder()
            .clientId(10L) // Le asignamos un ID al cliente
            .nombre("Juan")
            .apellido("Perez")
            .dni("123456")
            .activo(true)
            .build();
    accountEjemplo= Account.builder()
            .accountId(1L)
            .cliente(clientePropietario)
            .accountNur("2226654")
            .type("")
            .currency(Currency.USD)
            .balance(1000000)
            .build();
  }

  @DisplayName("")
  @Test
  void whenGetAccountById_thenReturnAccount(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Account resultado= accountService.getAccountById(1L);

    assertNotNull(resultado);
    assertNotNull(resultado.getAccountId());
    assertEquals(1L, resultado.getAccountId());

    verify(accountRepository , times(1)).findById(1L);
  }
  @DisplayName("")
  @Test
  void whenCreateAccount_thenReturnAccount(){
    Account cuentaCrear= Account.builder()
            .cliente(clientePropietario)
            .accountNur("2226654")
            .balance(1000000)
            .currency(Currency.USD)
            .build();

    when(clientService.getClientById(clientePropietario.getClientId())).thenReturn(clientePropietario);
    when(accountRepository.save(any(Account.class))).thenReturn(accountEjemplo);

    Account resultado= accountService.createAccount(cuentaCrear);

    assertNotNull(resultado);
    assertEquals(accountEjemplo, resultado);
    assertEquals(1L, resultado.getAccountId());
    assertNotNull(resultado.getCliente()); // Comprobamos que mantenga al cliente asociado
    assertEquals(10L, resultado.getCliente().getClientId());

    verify(clientService, times(1)).getClientById(clientePropietario.getClientId());
    verify(accountRepository, times(1)).save(any(Account.class));
  }

  @DisplayName("")
  @Test
  void whenUpdatedExistingAccount_thenReturnUpdatedAccount(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Account cuentaModificar= Account.builder()
            .accountId(1L)
            .accountNur("333333")
            .build();
    Account cuentaModificada=Account.builder()
            .accountId(1L)
            .cliente(clientePropietario)
            .accountNur("333333")
            .type("")
            .currency(Currency.USD)
            .balance(1000000)
            .build();

    when(accountRepository.save(any(Account.class))).thenReturn(cuentaModificada);

    Account resultado= accountService.updateAccount(cuentaModificar);

    assertNotNull(resultado);
    assertEquals("333333", resultado.getAccountNur());

    verify(accountRepository, times(1)).findById(1L);
    verify(accountRepository, times(1)).save(any(Account.class));
  }

  @DisplayName("")
  @Test
  void whenGetAccountById_thenDeleteAccount(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Long idEliminar=1L;

    accountService.deleteAccount(idEliminar);

    verify(accountRepository, times(1)).findById(1L);
  }

  @DisplayName("")
  @Test
  void whenGetAccountById_thenReturnAccountPesificada(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Dolar dolarMock = new Dolar();
    dolarMock.setCompra(1462.8); // Cotización ficticia a $1000 pesos por dólar
    when(dolarApiService.obtenerDolar("bolsa")).thenReturn(dolarMock);

    double montoPesificado= accountService.pesificar(1L);

    double valorEsperado = 1000000.0 * 1462.8;
    assertEquals(valorEsperado, montoPesificado);

    verify(accountRepository, times(1)).findById(1L);
    verify(dolarApiService, times(1)).obtenerDolar("bolsa");
  }

}
