package py.com.ecommerce.exception.Controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import py.com.ecommerce.exception.DTO.ExceptionResponseDto;

@RestControllerAdvice
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionResponseDto> handleResponseStatusException(DataIntegrityViolationException ex) {

        int statusCode = HttpStatus.CONFLICT.value();
        String statusName = HttpStatus.CONFLICT.getReasonPhrase();

        ExceptionResponseDto errorDto = new ExceptionResponseDto(
                statusCode,
                statusName,
                ex.getMessage()
        );

        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
    }

}