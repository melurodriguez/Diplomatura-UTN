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
  public Cliente updateClient(Cliente clienteConNuevosDatos) {

    Cliente existente = clientRepository.findById(clienteConNuevosDatos.getClientId())
            .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + clienteConNuevosDatos.getClientId()));

    if (clienteConNuevosDatos.getNombre() != null && !clienteConNuevosDatos.getNombre().isBlank()) {
      existente.setNombre(clienteConNuevosDatos.getNombre());
    }
    if (clienteConNuevosDatos.getDireccion() != null && !clienteConNuevosDatos.getDireccion().isBlank()) {
      existente.setDireccion(clienteConNuevosDatos.getDireccion());
    }
    if (clienteConNuevosDatos.getTelefono() != null && !clienteConNuevosDatos.getTelefono().isBlank()) {
      existente.setTelefono(clienteConNuevosDatos.getTelefono());
    }
    if (clienteConNuevosDatos.getEmail() != null && !clienteConNuevosDatos.getEmail().isBlank()) {
      existente.setEmail(clienteConNuevosDatos.getEmail());
    }
    if (existente.isActivo() != clienteConNuevosDatos.isActivo()){
      existente.setActivo(clienteConNuevosDatos.isActivo());
    }

    if (clienteConNuevosDatos.getSaldoPendiente() >= 0) {
      existente.setSaldoPendiente(clienteConNuevosDatos.getSaldoPendiente());
    }

    // PERSONA FÍSICA
    if (clienteConNuevosDatos instanceof ClientePersonaFisica && existente instanceof ClientePersonaFisica) {
      ClientePersonaFisica nuevosDatosFisica = (ClientePersonaFisica) clienteConNuevosDatos;
      ClientePersonaFisica existenteFisica = (ClientePersonaFisica) existente;

      if (nuevosDatosFisica.getApellido() != null && !nuevosDatosFisica.getApellido().isBlank()) {
        existenteFisica.setApellido(nuevosDatosFisica.getApellido());
      }
      if (nuevosDatosFisica.getDni() != null && !nuevosDatosFisica.getDni().isBlank()) {
        existenteFisica.setDni(nuevosDatosFisica.getDni());
      }
    }

    //EMPRESA
    if (clienteConNuevosDatos instanceof ClienteEmpresa && existente instanceof ClienteEmpresa) {
      ClienteEmpresa nuevosDatosEmpresa = (ClienteEmpresa) clienteConNuevosDatos;
      ClienteEmpresa existenteEmpresa = (ClienteEmpresa) existente;

      if (nuevosDatosEmpresa.getRazonSocial() != null && !nuevosDatosEmpresa.getRazonSocial().isBlank()) {
        existenteEmpresa.setRazonSocial(nuevosDatosEmpresa.getRazonSocial());
      }
      if (nuevosDatosEmpresa.getCuit() != null && !nuevosDatosEmpresa.getCuit().isBlank()) {
        existenteEmpresa.setCuit(nuevosDatosEmpresa.getCuit());
      }
    }

    return clientRepository.save(existente);
  }

  public void deleteClient(long id){
    Cliente existente= clientRepository.findById(id)
                    .orElseThrow(()->new ClientNotFoundException("No se encontró el usuario con ID: " + id));

    clientRepository.deleteById(id);
  }
}
