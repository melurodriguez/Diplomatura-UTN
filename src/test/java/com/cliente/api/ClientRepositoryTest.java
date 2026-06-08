package com.cliente.api;

import com.cliente.api.client.ClientRepository;
import com.cliente.api.client.Cliente;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.cliente.api.client.ClientePersonaFisica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
//El objetivo: Probar la interacción real con la base de datos (queries personalizadas con @Query, métodos como findByEmail, o configuraciones de auditoría).
@DataJpaTest
public class ClientRepositoryTest {

  @Autowired
  private ClientRepository clientRepository;

  @Test
  void debeGuardarPersonaFisica(){
    // GIVEN: Creación de la subclase concreta usando su Builder
    ClientePersonaFisica persona = ClientePersonaFisica.builder()
            // Atributos de la clase madre (Cliente)
            .nombre("test")
            .email("test@ejemplo.com")
            .direccion("Calle 1234")
            .activo(true)
            .telefono("333333333")
            .saldoPendiente(10000000)
            .fechaAlta(LocalDate.now())
            // Atributos exclusivos de la clase hija (PersonaFisica)
            .apellido("primero")
            .dni("44445556")
            .build();

    // WHEN: Se guarda usando el mismo repositorio polimórfico
    Cliente savedClient = clientRepository.save(persona);

    // THEN: Validaciones
    assertThat(savedClient).isNotNull();
    assertThat(savedClient.getClientId()).isNotNull();
    assertThat(savedClient.getNombre()).isEqualTo("test");

    // Opcional: Validar que la base de datos reconozca que es una PersonaFisica
    assertThat(savedClient).isInstanceOf(ClientePersonaFisica.class);
    assertThat(((ClientePersonaFisica) savedClient).getDni()).isEqualTo("44445556");
  }
}
