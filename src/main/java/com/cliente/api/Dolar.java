package com.cliente.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
@Setter

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
  private Date fechaActualizacion;

  public Dolar() {
  }

  public Dolar(String moneda, String casa, String nombre, double compra, double venta) {
    this.moneda = moneda;
    this.casa = casa;
    this.nombre = nombre;
    this.compra = compra;
    this.venta = venta;
  }


}
