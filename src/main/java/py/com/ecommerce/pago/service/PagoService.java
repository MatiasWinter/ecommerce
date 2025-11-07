package py.com.ecommerce.pago.service;

import py.com.ecommerce.pago.DTO.PagoRequestDto;
import py.com.ecommerce.pago.DTO.PagoResponseDto;

public interface PagoService {
    PagoResponseDto confirmarPago(PagoRequestDto pagoRequestDto);
}
