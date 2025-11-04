package py.com.ecommerce.cliente.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EliminarClienteRespuestaDto {
    private boolean exito;
    private String mensaje;
}
