package py.com.ecommerce.cliente.Controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import py.com.ecommerce.cliente.DTO.EliminarClienteRespuestaDto;
import py.com.ecommerce.cliente.entity.ClienteEntity;
import py.com.ecommerce.cliente.service.ClienteService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/{id}")
    public ClienteEntity getClientById(@PathVariable(name = "id") Long id) {
        return clienteService.findById(id);
    }

    @GetMapping("/email/{email}")
    public ClienteEntity getClientByEmail(@PathVariable(name = "email") String email) {
        return clienteService.findByEmail(email);
    }

    @GetMapping("/all")
    public List<ClienteEntity> getAllClient() {
        return clienteService.getAll();
    }

    @PostMapping("/save")
    public ClienteEntity saveOrUpdate(@RequestBody ClienteEntity cliente) {
        return clienteService.saveOrUpdate(cliente);
    }

    @DeleteMapping("/{id}")
    public EliminarClienteRespuestaDto delete(@PathVariable Long id) {
        return clienteService.delete(id);
    }
}
