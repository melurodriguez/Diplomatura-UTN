package com.cliente.api.client;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SuperBuilder
public abstract class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "client_id")
  private Long clientId;

  private String nombre;
  private String direccion;
  private String telefono;
  private String email;
  private boolean activo;
  private double saldoPendiente;
  private LocalDate fechaAlta;

  private final int DIAS_UMBRAL=30;

  private boolean esClienteReciente(){
    if(fechaAlta == null) return false;
    return !fechaAlta.isBefore(LocalDate.now().minusDays(DIAS_UMBRAL));
  }
}