package com.cliente.api;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ClientRepositoryTest {

  @Autowired
  private ClientRepository clientRepository;

  @Test
  void debeGuardarCliente(){
    //GIVEN: creacion del cliente
    Cliente toAddClient = new Cliente();
    toAddClient.setNombre("test");
    toAddClient.setApellido("primero");
    toAddClient.setDni("44445556");
    toAddClient.setEmail("test@ejemplo.com");
    toAddClient.setDireccion("Calle 1234");
    toAddClient.setTipoCliente("PERSONA FISICA");
    toAddClient.setActivo(true);
    toAddClient.setTelefono("333333333");
    toAddClient.setSaldoPendiente(10000000);
    toAddClient.setFechaAlta(LocalDate.now());
    //WHEN: se ejecute la funcionalidad clientRepository.save(toAddClient)
    Cliente savedClient = clientRepository.save(toAddClient);
    //THEN: debe devolver cliente. assertThat(toAddCliente.getId())
    assertThat(savedClient).isNotNull();

    assertThat(savedClient.getId()).isNotNull();

    assertThat(savedClient.getNombre()).isEqualTo("test");
  }
}
