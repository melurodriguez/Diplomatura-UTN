package com.cliente.api.dolar;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class DolarClient {

  private final RestClient dolarApiClient= RestClient.create();

  @Value("${dolar.api.url}")  //me permite acc a la url alm en el yaml
  private String dolarUrl;

  public Dolar getDolar(String tipoDolar){

    return dolarApiClient.get()
            .uri(dolarUrl + tipoDolar)
            .accept(APPLICATION_JSON)
            .retrieve()
            .body(Dolar.class);
  }
}
