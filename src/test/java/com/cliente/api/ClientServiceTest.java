package com.cliente.api;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cliente.api.client.*;
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

  private ClientePersonaFisica personaFisicaEjemplo;
  private ClienteEmpresa empresaEjemplo;

  @BeforeEach
  void setUp() {
    personaFisicaEjemplo= ClientePersonaFisica.builder()
            .clientId(1L)
            .nombre("Juan")
            .direccion("Calle 123")
            .telefono("1111")
            .email("juan@mail.com")
            .activo(true)
            .saldoPendiente(1000000)
            .fechaAlta(LocalDate.now())
            .apellido("Perez")
            .dni("123456")
            .build();

    empresaEjemplo = ClienteEmpresa.builder()
            .clientId(2L)
            .nombre("Tech Solutions")
            .direccion("Av. Corrientes 500")
            .telefono("2222")
            .email("contacto@tech.com")
            .activo(true)
            .saldoPendiente(5000000)
            .fechaAlta(LocalDate.now())
            .razonSocial("Tech Solutions S.A.")
            .cuit("30-12345678-9")
            .build();
  }

  // ==========================================
  // 1. TESTS DE BÚSQUEDA (GET BY ID)
  // ==========================================

  @DisplayName("Dado un id de Persona Física, devuelve el usuario correspondiente")
  @Test
  void whenGetClientById_thenReturnPersonaFisica() {
    when(clientRepository.findById(1L)).thenReturn(Optional.of(personaFisicaEjemplo));
    Cliente resultado = clientService.getClientById(1L);

    assertNotNull(resultado);
    assertEquals("Juan", resultado.getNombre());
    assertInstanceOf(ClientePersonaFisica.class, resultado);
    verify(clientRepository, times(1)).findById(1L);
  }

  @DisplayName("Dado un id de Empresa, devuelve la empresa correspondiente")
  @Test
  void whenGetClientById_thenReturnEmpresa() {
    when(clientRepository.findById(2L)).thenReturn(Optional.of(empresaEjemplo));
    Cliente resultado = clientService.getClientById(2L);

    assertNotNull(resultado);
    assertEquals("Tech Solutions S.A.", ((ClienteEmpresa) resultado).getRazonSocial());
    assertInstanceOf(ClienteEmpresa.class, resultado);
    verify(clientRepository, times(1)).findById(2L);
  }

  // ==========================================
  // 2. TESTS DE CREACIÓN (POST)
  // ==========================================

  @DisplayName("Debe crear un cliente de tipo persona fisica y devolverlo")
  @Test
  void whenCreatePersonaFisica_thenReturnPersonaFisica(){
    ClientePersonaFisica clienteAGuardar = ClientePersonaFisica.builder()
            .nombre("Juan").apellido("Perez").dni("123456").build();

    when(clientRepository.save(any(ClientePersonaFisica.class))).thenReturn(personaFisicaEjemplo);
    Cliente resultado = clientService.addClient(clienteAGuardar);

    assertNotNull(resultado);
    assertEquals(1L, resultado.getClientId());
    verify(clientRepository, times(1)).save(any(ClientePersonaFisica.class));
  }

  @DisplayName("Debe crear un cliente de tipo empresa y devolverlo")
  @Test
  void whenCreateEmpresa_thenReturnEmpresa(){
    ClienteEmpresa empresaAGuardar = ClienteEmpresa.builder()
            .nombre("Tech Solutions").razonSocial("Tech Solutions S.A.").cuit("30-12345678-9").build();

    when(clientRepository.save(any(ClienteEmpresa.class))).thenReturn(empresaEjemplo);
    Cliente resultado = clientService.addClient(empresaAGuardar);

    assertNotNull(resultado);
    assertEquals(2L, resultado.getClientId());
    assertEquals("30-12345678-9", ((ClienteEmpresa) resultado).getCuit());
    verify(clientRepository, times(1)).save(any(ClienteEmpresa.class));
  }

  // ==========================================
  // 3. TESTS DE ACTUALIZACIÓN (PUT)
  // ==========================================

  @DisplayName("Dado un cliente existente (Persona Fisica) a actualizar debe devolver el cliente modificado")
  @Test
  void whenUpdatedExistingClient_thenReturnUpdatedClient(){
    when(clientRepository.findById(1L)).thenReturn(Optional.of(personaFisicaEjemplo));

    ClientePersonaFisica clienteConNuevosDatos = ClientePersonaFisica.builder()
            .clientId(1L).nombre("Martin").build();

    ClientePersonaFisica clienteYaActualizado = ClientePersonaFisica.builder()
            .clientId(1L).nombre("Martin").apellido("Perez").dni("123456").activo(true).build();

    when(clientRepository.save(any(ClientePersonaFisica.class))).thenReturn(clienteYaActualizado);
    Cliente resultado = clientService.updateClient(clienteConNuevosDatos);

    assertNotNull(resultado);
    assertEquals("Martin", resultado.getNombre());
    verify(clientRepository, times(1)).findById(1L);
    verify(clientRepository, times(1)).save(any(ClientePersonaFisica.class));
  }

  @DisplayName("Dada una empresa existente a actualizar debe mapear y devolver la empresa modificada")
  @Test
  void whenUpdatedExistingEmpresa_thenReturnUpdatedEmpresa(){
    when(clientRepository.findById(2L)).thenReturn(Optional.of(empresaEjemplo));

    ClienteEmpresa empresaConNuevosDatos = ClienteEmpresa.builder()
            .clientId(2L).razonSocial("Tech Innovación S.A.").build();

    ClienteEmpresa empresaYaActualizada = ClienteEmpresa.builder()
            .clientId(2L).nombre("Tech Solutions").razonSocial("Tech Innovación S.A.").cuit("30-12345678-9").activo(true).build();

    when(clientRepository.save(any(ClienteEmpresa.class))).thenReturn(empresaYaActualizada);
    Cliente resultado = clientService.updateClient(empresaConNuevosDatos);

    assertNotNull(resultado);
    assertEquals("Tech Innovación S.A.", ((ClienteEmpresa) resultado).getRazonSocial());
    assertEquals("Tech Solutions", resultado.getNombre());
    verify(clientRepository, times(1)).findById(2L);
    verify(clientRepository, times(1)).save(any(ClienteEmpresa.class));
  }

  // ==========================================
  // 4. TEST DE ELIMINACIÓN (DELETE)
  // ==========================================

  @DisplayName("Dado un id, cuando se ejecuta el metodo se debe eliminar el cliente correspondiente")
  @Test
  void whenDeleteById_thenReturnsNothing(){
    when(clientRepository.findById(1L)).thenReturn(Optional.of(personaFisicaEjemplo));

    clientService.deleteClient(1L);

    verify(clientRepository, times(1)).findById(1L);
    verify(clientRepository, times(1)).deleteById(1L);
  }

}