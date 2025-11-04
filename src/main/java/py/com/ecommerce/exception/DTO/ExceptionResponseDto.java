package py.com.ecommerce.exception.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDto {
    private int status;
    private String error;
    private String message;
}
