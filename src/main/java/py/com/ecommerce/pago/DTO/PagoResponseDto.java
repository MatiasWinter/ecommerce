package py.com.ecommerce.pago.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagoResponseDto {
    private String cola;
    private boolean enviado;
    private String mensaje;

}