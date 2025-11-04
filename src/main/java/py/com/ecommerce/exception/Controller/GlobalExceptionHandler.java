package py.com.ecommerce.exception.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import py.com.ecommerce.exception.DTO.ExceptionResponseDto;

@RestControllerAdvice // Esta anotación aplica este manejo a todos los controladores
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(ResponseStatusException ex) {

        int statusCode = ex.getStatusCode().value();
        String statusName = ex.getStatusCode().toString();

        ExceptionResponseDto errorDto = new ExceptionResponseDto(
                statusCode,
                statusName,
                ex.getReason()
        );

        return new ResponseEntity<>(errorDto, ex.getStatusCode());
    }

}