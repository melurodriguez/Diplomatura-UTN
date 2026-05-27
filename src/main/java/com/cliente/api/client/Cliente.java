package com.cliente.api.client;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "client_id")
  private Long clientId;

  private String nombre;
  private String apellido;
  private String dni;
  private String direccion;
  private String telefono;
  private String email;

  private String tipoCliente; //"PERSONA_FISICA" "EMPRESA"
  private boolean activo;
  private double saldoPendiente;
  private LocalDate fechaAlta;

  private final int DIAS_UMBRAL=30;

  private boolean esClienteReciente(){
    if(fechaAlta == null) return false;
    return !fechaAlta.isBefore(LocalDate.now().minusDays(DIAS_UMBRAL));
  }
}