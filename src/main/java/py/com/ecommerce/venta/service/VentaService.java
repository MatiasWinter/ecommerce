package py.com.ecommerce.venta.service;

import py.com.ecommerce.venta.DTO.VentaRequestDto;
import py.com.ecommerce.venta.DTO.VentaResponseDto;

public interface VentaService {
    VentaResponseDto vender(VentaRequestDto ventaRequestDto);
}
