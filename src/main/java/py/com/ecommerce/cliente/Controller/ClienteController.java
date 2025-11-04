package py.com.ecommerce.cliente.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import py.com.ecommerce.cliente.DTO.EliminarClienteRespuestaDto;
import py.com.ecommerce.cliente.entity.ClienteEntity;
import py.com.ecommerce.cliente.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
@Tag(name = "Cliente", description = "Operaciones CRUD para la tabla cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(
            summary = "Obtener cliente por ID",
            description = "Devuelve los datos de un cliente específico usando su ID."
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{id}")
    public ClienteEntity getClientById(
            @Parameter(description = "ID del cliente a consultar", example = "1")
            @PathVariable Long id) {
        return clienteService.findById(id);
    }

    @Operation(summary = "Buscar cliente por su email")
    @GetMapping("/email/{email}")
    public ClienteEntity getClientByEmail(
            @Parameter(description = "Email del cliente", example = "matias.franco@gmail.com")
            @PathVariable String email) {
        return clienteService.findByEmail(email);
    }

    @Operation(summary = "Lista todos los clientes")
    @GetMapping("/all")
    public List<ClienteEntity> getAllClient() {
        return clienteService.getAll();
    }

    @Operation(summary = "Crea un nuevo cliente")
    @PostMapping("/save")
    public ClienteEntity save(
            @Parameter(description = "Datos del cliente a crear")
            @RequestBody ClienteEntity cliente) {
        return clienteService.createCliente(cliente);
    }

    @Operation(summary = "Editar un cliente existente")
    @PutMapping("/edit/{id}")
    public ClienteEntity edit(
            @Parameter(description = "ID del cliente a editar", example = "6")
            @PathVariable Long id,
            @RequestBody ClienteEntity cliente) {
        return clienteService.updateCliente(id, cliente);
    }

    @Operation(summary = "Eliminar un cliente por ID")
    @DeleteMapping("/{id}")
    public EliminarClienteRespuestaDto delete(
            @Parameter(description = "ID del cliente a eliminar", example = "6")
            @PathVariable Long id) {
        return clienteService.delete(id);
    }
}
