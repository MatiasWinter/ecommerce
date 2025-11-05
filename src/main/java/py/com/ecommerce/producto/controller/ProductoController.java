package py.com.ecommerce.producto.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import py.com.ecommerce.producto.entity.ProductoEntity;
import py.com.ecommerce.producto.service.ProductoService;

@Tag(
        name = "Producto",
        description = "Controlador que gestiona las operaciones relacionadas con los productos del sistema."
)
@RestController
@RequestMapping("/producto")
@AllArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @Operation(
            summary = "Obtener producto por ID",
            description = "Devuelve la información completa de un producto según su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ProductoEntity get(
            @Parameter(description = "ID del producto a buscar", example = "1")
            @PathVariable Long id
    ) {
        return productoService.findById(id);
    }

    @Operation(
            summary = "Crea un nuevo producto",
            description = "Registra un nuevo producto en el sistema."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PostMapping("/create")
    public ProductoEntity create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON con los datos del nuevo producto",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductoEntity.class))
            )
            @RequestBody ProductoEntity producto
    ) {
        return productoService.create(producto);
    }

    @Operation(
            summary = "Actualizar producto existente",
            description = "Modifica los datos de un producto ya registrado usando su ID."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductoEntity.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos en la solicitud")
    })
    @PutMapping("/update/{id}")
    public ProductoEntity update(
            @Parameter(description = "ID del producto a actualizar", example = "1")
            @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON con los nuevos datos del producto",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ProductoEntity.class))
            )
            @RequestBody ProductoEntity producto
    ) {
        return productoService.update(id, producto);
    }
}
