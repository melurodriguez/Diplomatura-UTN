package com.cliente.api.dolar;

import com.cliente.api.dolar.Dolar;
import com.cliente.api.dolar.DolarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
//RestTemplate: consume APIs REST externas desde la aplicación, hace las peticiones HTTP, tranforma la rta a Dolar
//getForObject()	GET y devuelve objeto
//getForEntity()	GET y devuelve objeto + headers + status
//postForObject()	POST
//put()	PUT
//delete()	DELETE

@Service
public class DolarApiService {

  @Autowired
  private DolarClient dolarClient;

  private final RestClient dolarApiClient= RestClient.create();

  public Dolar obtenerDolar(String tipoDolar){
    return dolarClient.getDolar(tipoDolar);
  }

}
