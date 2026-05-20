package com.cliente.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//RestTemplate: consume APIs REST externas desde la aplicación, hace las peticiones HTTP, tranforma la rta a Dolar
//getForObject()	GET y devuelve objeto
//getForEntity()	GET y devuelve objeto + headers + status
//postForObject()	POST
//put()	PUT
//delete()	DELETE

@Service
public class DolarApiService {

  private final RestTemplate restTemplate=new RestTemplate();

  public  Dolar obtenerDolar(String tipoDolar){
    String url= "https://dolarapi.com/v1/dolares/" + tipoDolar;

    return restTemplate.getForObject(url, Dolar.class);
  }
}
