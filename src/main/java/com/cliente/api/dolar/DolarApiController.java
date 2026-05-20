package com.cliente.api.dolar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dolarApi")
public class DolarApiController {

  private final DolarApiService dolarApiService;

  public DolarApiController(DolarApiService dolarApiService) {
    this.dolarApiService = dolarApiService;
  }

  @GetMapping("/{tipo_dolar}")
  public Dolar getDolarPorTipo(@PathVariable String tipo_dolar){
    return dolarApiService.obtenerDolar(tipo_dolar);
  }
}
