package com.cliente.api.client;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cuenta no encontrada")
public class ClientNotFoundException extends Throwable{

  public ClientNotFoundException(String message){
    super(message);
  }
}
