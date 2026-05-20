package com.cliente.api.dolar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Component
@Setter
@Builder
public class Dolar {

  @Setter
  private String moneda;
  @Setter
  private String casa;
  @Setter
  private String nombre;
  @Setter
  private double compra;
  @Setter
  private double venta;
  private LocalDate fechaActualizacion;

  public Dolar(){

  }

  public Dolar(String moneda, String casa, String nombre, double compra, double venta, LocalDate fechaActualizacion) {
    this.moneda = moneda;
    this.casa = casa;
    this.nombre = nombre;
    this.compra = compra;
    this.venta = venta;
    this.fechaActualizacion = fechaActualizacion;
  }


}
