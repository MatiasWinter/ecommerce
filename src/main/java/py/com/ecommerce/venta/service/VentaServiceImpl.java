package py.com.ecommerce.venta.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ResponseStatusException;
import py.com.ecommerce.cliente.enums.CLIENTE_ESTADO;
import py.com.ecommerce.cliente.entity.ClienteEntity;
import py.com.ecommerce.cliente.repository.ClienteRepository;
import py.com.ecommerce.venta.DTO.VentaRequestDto;
import py.com.ecommerce.venta.DTO.VentaResponseDto;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class VentaServiceImpl implements VentaService{
    private final RestClient restClient;
    private final String URL_SELL = "http://localhost:8085/venta/sell";
    private final ClienteRepository clienteRepository;

    @Override
    public VentaResponseDto vender(VentaRequestDto ventaRequestDto) {
        try{
            log.info("Procedemos a verificar que el cliente con id {} existe y que este en estado {} para realizar la venta", ventaRequestDto.getIdCliente(),CLIENTE_ESTADO.ACTIVO);
            ClienteEntity clienteEntity = clienteRepository.findById(ventaRequestDto.getIdCliente()).orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No pudimos encontrar el cliente con ID: " + ventaRequestDto.getIdCliente()
            ));
            if (!clienteEntity.getEstado().equals(CLIENTE_ESTADO.ACTIVO.name())) throw new ResponseStatusException(HttpStatus.FORBIDDEN,"El cliente no esta en estado " + CLIENTE_ESTADO.ACTIVO);

            log.info("Procedemos a llamar al servicio de venta para la venta para el cliente con id {}",ventaRequestDto.getIdCliente());
            return restClient.post().uri(URL_SELL).body(ventaRequestDto).retrieve().body(VentaResponseDto.class);
        } catch (RestClientException e) {
            log.error("Error al llamar al servicio de ventas: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR," Error al llamar al componentes de ventas " + e.getMessage());
        }
    }
}
