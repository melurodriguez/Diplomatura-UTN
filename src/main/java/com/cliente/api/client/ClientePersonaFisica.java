package com.cliente.api.client;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @Setter @NoArgsConstructor
@SuperBuilder
public class ClientePersonaFisica extends Cliente {
  private String apellido;
  private String dni;

}
