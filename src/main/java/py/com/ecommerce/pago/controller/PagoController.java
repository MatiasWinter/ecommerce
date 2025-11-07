package py.com.ecommerce.pago.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import py.com.ecommerce.pago.DTO.PagoRequestDto;
import py.com.ecommerce.pago.DTO.PagoResponseDto;
import py.com.ecommerce.pago.service.PagoService;

@RestController
@RequestMapping("/pago")
@AllArgsConstructor
@Tag(name = "Pago", description = "Operaciones relacionadas con los pagos de clientes")
public class PagoController {

    private final PagoService pagoService;

    @Operation(
            summary = "Confirmar un pago",
            description = "Envía la solicitud de pago al sistema de ventas a través de RabbitMQ para su procesamiento.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pago enviado correctamente a la cola RabbitMQ",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PagoResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Datos del pago inválidos o incompletos",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno al enviar el mensaje al sistema de ventas",
                            content = @Content
                    )
            }
    )
    @PostMapping("/confirm")
    public PagoResponseDto confirmarPago(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para confirmar un pago",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PagoRequestDto.class))
            )
            @RequestBody PagoRequestDto pagoRequestDto
    ) {
        return pagoService.confirmarPago(pagoRequestDto);
    }
}
