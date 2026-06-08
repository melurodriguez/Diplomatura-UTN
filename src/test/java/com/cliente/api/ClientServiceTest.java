package com.cliente.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cliente.api.client.ClientRepository;
import com.cliente.api.client.ClientService;
import com.cliente.api.client.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class) // Habilita el uso de mocks
class ClientServiceTest {

  @Mock //indicca que es un objeto simulado
  private ClientRepository clientRepository;

  @InjectMocks  //la clase recibira como dependencia el mock
  private ClientService clientService;

  private Cliente clienteEjemplo;

  @BeforeEach
  void setUp() {
    clienteEjemplo = new Cliente(1L, "Juan", "Perez", "123456", "Calle 123", "1111", "juan@mail.com", "PERSONA FISICA", true, 1000000, LocalDate.now());
  }

  @DisplayName("Dado un id cuando se pasa al metodo, devuelve un usuario")
  @Test
  void whenGetClientById_thenReturnCliente() {
    // Configuración del Mock: Cuando el repo busque el ID 1, devuelve el cliente ejemplo
    when(clientRepository.findById(1L))
            .thenReturn(Optional.of(clienteEjemplo));

    Cliente resultado = clientService.getClientById(1L);

    assertNotNull(resultado);
    assertEquals("Juan", resultado.getNombre());
    // Verificamos que el repositorio fue llamado exactamente una vez
    verify(clientRepository, times(1)).findById(1L);
  }
}