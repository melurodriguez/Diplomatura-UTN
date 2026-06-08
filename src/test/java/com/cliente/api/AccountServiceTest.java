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
import com.cliente.api.account.AccountNotFoundException;

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
  // ==========================================
  // 1. TEST DE OBTENER POR ID(GET)
  // ==========================================
  @DisplayName("Dado un ID válido, se debe devolver la cuenta asociada al mismo")
  @Test
  void whenGetAccountById_thenReturnAccount(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Account resultado= accountService.getAccountById(1L);

    assertNotNull(resultado);
    assertNotNull(resultado.getAccountId());
    assertEquals(1L, resultado.getAccountId());

    verify(accountRepository , times(1)).findById(1L);
  }
  @DisplayName("Dado un ID inválido, se debe devolver la excepción")
  @Test
  void whenGetAccountByInvalidId_thenReturnException(){
    when(accountRepository.findById(5L)).thenReturn(Optional.empty());

    Long invalidID=5L;

    AccountNotFoundException exception= assertThrows(AccountNotFoundException.class, ()->{
      accountService.getAccountById(invalidID);
    });

    assertEquals("No se encontró la cuenta con ID: 5", exception.getMessage());

    verify(accountRepository , times(1)).findById(5L);
  }


  // ==========================================
  // 2. TEST DE CREACÓN(POST)
  // ==========================================
  @DisplayName("Dada una nueva cuenta, se debe crear y devolver la misma")
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


  @DisplayName("Dada una cuenta inválida, al no tener un cliente asociado, debe lanzar IllegalArgumentException")
  @Test
  void whenCreateAccountWithoutClient_thenThrowIllegalArgumentException() {
    Account cuentaCrear= Account.builder()
            .accountNur("2226654")
            .balance(1000000)
            .currency(Currency.USD)
            .build();


    IllegalArgumentException excepcionLanzada = assertThrows(IllegalArgumentException.class, () -> {
      accountService.createAccount(cuentaCrear);
    });

    assertEquals("La cuenta debe estar asociada a un cliente.", excepcionLanzada.getMessage());

    verify(accountRepository, never()).save(any(Account.class));

  }

  // ==========================================
  // 3. TESTS DE ACTUALIZACION(PUT)
  // ==========================================

  @DisplayName("Dado un ID válido, se debe modificar la cuenta asociada")
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

  @DisplayName("Dado un ID inválido, se debe lanzar excepción")
  @Test
  void whenUpdatedNotExistingAccount_thenReturnException(){
    when(accountRepository.findById(5L)).thenReturn(Optional.empty());

    Account cuentaModificar= Account.builder()
            .accountId(5L)
            .accountNur("333333")
            .build();

    AccountNotFoundException exception= assertThrows(AccountNotFoundException.class, ()->{
      accountService.updateAccount(cuentaModificar);
    });


    assertEquals("No se encontró la cuenta con ID: 5", exception.getMessage());

    verify(accountRepository, times(1)).findById(5L);
    verify(accountRepository, never()).save(any(Account.class));
  }

  // ==========================================
  // 4. TEST DE BORRADO(DELETE)
  // ==========================================
  @DisplayName("Dado un ID válido, se debe eliminar la cuenta asociada al mismo")
  @Test
  void whenGetAccountById_thenDeleteAccount(){
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    Long idEliminar=1L;

    accountService.deleteAccount(idEliminar);

    verify(accountRepository, times(1)).findById(1L);
  }
  @DisplayName("Dado un ID inválido, se debe lamzar excepción")
  @Test
  void whenDeleteAccountByInvalidId_thenReturnException(){
    when(accountRepository.findById(5L)).thenReturn(Optional.empty());

    Long idEliminar=5L;

    AccountNotFoundException exception= assertThrows(AccountNotFoundException.class,()->{
      accountService.deleteAccount(idEliminar);
    });

    assertEquals("No se encontró la cuenta con ID: 5", exception.getMessage());

    verify(accountRepository, times(1)).findById(5L);
    verify(accountRepository, never()).deleteById(idEliminar);
  }


  // ==========================================
  // 5. TEST DE PESIFICAR CUENTA(GET)
  // ==========================================

  @DisplayName("Dado un ID válido y una cuenta en USD, se debe pesificar el balance de la misma")
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

  @DisplayName("Dado un ID de cuenta inexistente, al intentar pesificar debe lanzar AccountNotFoundException")
  @Test
  void whenPesificarWithInvalidId_thenThrowAccountNotFoundException() {
    Long idInexistente = 99L;
    when(accountRepository.findById(idInexistente)).thenReturn(Optional.empty());

    AccountNotFoundException excepcionLanzada = assertThrows(AccountNotFoundException.class, () -> {
      accountService.pesificar(idInexistente);
    });

    assertEquals("No se encontró la cuenta con ID: 99", excepcionLanzada.getMessage());

    verify(accountRepository, times(1)).findById(idInexistente);
    verify(dolarApiService, never()).obtenerDolar(anyString());
  }

  @DisplayName("Dada una cuenta válida, si la API del dólar falla, debe lanzar RuntimeException")
  @Test
  void whenDolarApiFails_thenThrowRuntimeException() {
    when(accountRepository.findById(1L)).thenReturn(Optional.of(accountEjemplo));

    when(dolarApiService.obtenerDolar("bolsa")).thenReturn(null);

    RuntimeException excepcionLanzada = assertThrows(RuntimeException.class, () -> {
      accountService.pesificar(1L);
    });

    assertEquals("No se pudo obtener el valor del Dólar MEP actual", excepcionLanzada.getMessage());

    verify(accountRepository, times(1)).findById(1L);
    verify(dolarApiService, times(1)).obtenerDolar("bolsa");
  }



}
