package py.com.ecommerce.cliente.service;

import py.com.ecommerce.cliente.entity.ClienteEntity;

import java.util.List;

public interface ClienteService{
    ClienteEntity findById(Long id);
    ClienteEntity findByEmail(String email);
    List<ClienteEntity> getAll();
    ClienteEntity createCliente(ClienteEntity cliente) ;
    ClienteEntity updateCliente(Long id, ClienteEntity cliente) ;
}
