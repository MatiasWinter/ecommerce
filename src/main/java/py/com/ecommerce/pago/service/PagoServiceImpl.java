package py.com.ecommerce.pago.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import py.com.ecommerce.config.RabbitMQConfig;
import py.com.ecommerce.pago.DTO.PagoRequestDto;
import py.com.ecommerce.pago.DTO.PagoResponseDto;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PagoServiceImpl implements PagoService{
    private final RabbitTemplate rabbitTemplate;

    @Override
    public PagoResponseDto confirmarPago(PagoRequestDto pagoRequestDto) {
        try {
            log.info("Procedemos a enviar el mensaje al queue: " + RabbitMQConfig.QUEUE_PAGO);
            rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_PAGO, pagoRequestDto);
            log.info("Mensaje enviado con exito al queue: " + RabbitMQConfig.QUEUE_PAGO);
            return new PagoResponseDto(RabbitMQConfig.QUEUE_PAGO,true,"Mensaje enviado con exito");
        }catch(Exception e) {
            log.error("Error al enviar el queue de venta", e);
            return new PagoResponseDto(RabbitMQConfig.QUEUE_PAGO,false,e.getMessage());
        }
    }
}
