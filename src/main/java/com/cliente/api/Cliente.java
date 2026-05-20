package com.cliente.api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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

  private boolean esClienteReciente(int diasUmbral){
    if(fechaAlta == null) return false;
    return !fechaAlta.isBefore(LocalDate.now().minusDays(diasUmbral));
  }
}