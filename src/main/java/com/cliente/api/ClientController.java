package com.cliente.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")

public class ClientController {

  @Autowired
  private ClientService clientService;


  @GetMapping("/")
  public List<Cliente> getClientes(){
    return clientService.getClientes();
  }

  @GetMapping("/{id}")
  public Cliente getClientById(@PathVariable long id) {
    return clientService.getClientById(id);
  }

  @PostMapping("/addClient")
  public Cliente addClient(@RequestBody Cliente client) {
    return clientService.addClient(client);
  }

  @PutMapping("/{id}")
  public Cliente updateClient(@PathVariable long id, @RequestBody Cliente cliente){
    return clientService.updateClient(cliente);
  }

  @DeleteMapping("/deleteClient/{id}")
  public void deleteClient(@PathVariable long id){
    clientService.deleteClient(id);
  }


}
