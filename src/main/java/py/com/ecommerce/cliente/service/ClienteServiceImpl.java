package py.com.ecommerce.cliente.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.com.ecommerce.cliente.DTO.EliminarClienteRespuestaDto;
import py.com.ecommerce.cliente.entity.ClienteEntity;
import py.com.ecommerce.cliente.repository.ClienteRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ClienteServiceImpl implements ClienteService{
    private final ClienteRepository clienteRepository;

    @Override
    public ClienteEntity findById(Long id) {
        log.info("Procedemos a buscar el cliente con ID {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No pudimos encontrar el cliente con ID: " + id
                ));
    }

    @Override
    public ClienteEntity findByEmail(String email) {
        log.info("Procedemos a buscar el cliente con email {}", email);
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No pudimos encontrar el cliente con email: " + email
                ));
    }

    @Override
    public List<ClienteEntity> getAll() {
        log.info("Procedemos a obtener todos los clientes de la base de datos");
        List<ClienteEntity> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()) {
            log.warn("La base de datos no contiene clientes.");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No existen clientes en la base de datos"
            );
        }
        log.info("Se obtuvo la lista de clientes con exito");
        return clientes;
    }

    public ClienteEntity createCliente(ClienteEntity cliente) {
        if (cliente.getIdCliente() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se debe enviar ID para crear un cliente.");
        }
        log.info("Procedemos a guardar el cliente nuevo");
        return clienteRepository.save(cliente);
    }


    public ClienteEntity updateCliente(Long id, ClienteEntity clienteRequest) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente con ID " + id + " no existe para actualizar."));
        log.info("Procedemos a actualizar el cliente {}", id);
        clienteRequest.setIdCliente(id);
        return clienteRepository.save(clienteRequest);
    }
}
