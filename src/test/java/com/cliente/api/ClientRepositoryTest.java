package com.cliente.api;

import com.cliente.api.client.ClientRepository;
import com.cliente.api.client.Cliente;
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
    Cliente cliente = Cliente.builder()
            .nombre("test")
            .apellido("primero")
            .dni("44445556")
            .email("test@ejemplo.com")
            .direccion("Calle 1234")
            .tipoCliente("PERSONA FISICA")
            .activo(true)
            .telefono("333333333")
            .saldoPendiente(10000000)
            .fechaAlta(LocalDate.now())
            .build();
    //WHEN: se ejecute la funcionalidad clientRepository.save(toAddClient)
    Cliente savedClient = clientRepository.save(cliente);
    //THEN: debe devolver cliente. assertThat(toAddCliente.getId())
    assertThat(savedClient).isNotNull();

    assertThat(savedClient.getId()).isNotNull();

    assertThat(savedClient.getNombre()).isEqualTo("test");
  }
}
