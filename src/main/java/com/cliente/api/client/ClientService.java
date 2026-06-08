package com.cliente.api.client;

import com.cliente.api.client.ClientRepository;
import com.cliente.api.client.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientService {

  @Autowired
  private ClientRepository clientRepository;


  public List<Cliente> getClientes(){
    return clientRepository.findAll();
  }

  public Cliente getClientById(long id){
    return clientRepository.findById(id).orElse(null);
  }

  public Cliente addClient(Cliente cliente){
    cliente.setFechaAlta(LocalDate.now());
    return clientRepository.save(cliente);
  }
  public Cliente updateClient(Cliente cliente){
    return clientRepository.save(cliente);
  }

  public void deleteClient(long id){
    clientRepository.deleteById(id);
  }
}
