package py.com.ecommerce.cliente.service;

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
        return clienteRepository.findAll();
    }

    @Override
    public ClienteEntity saveOrUpdate(ClienteEntity cliente) {
        log.info("Procedemos a persistir el cliente {}", cliente.getIdCliente());
        return clienteRepository.save(cliente);
    }

    @Override
    public EliminarClienteRespuestaDto delete(Long id) {
        try {
            log.info("Procedemos a eliminar el cliente con id {}", id);
            clienteRepository.deleteById(id);
            log.info("Cliente con id {} eliminado exitosamente.", id);

            return new EliminarClienteRespuestaDto(true, "El cliente con ID " + id + " ha sido eliminado correctamente.");

        } catch (Exception e) {
            log.error("Error al intentar eliminar cliente con ID: {}", id, e);
            return new EliminarClienteRespuestaDto(false, "No se pudo eliminar el cliente" + e.getMessage());
        }
    }
}
