package py.com.ecommerce.producto.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import py.com.ecommerce.producto.entity.ProductoEntity;
import py.com.ecommerce.producto.repository.ProductoRepository;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductoServiceImpl implements ProductoService{
    private ProductoRepository productoRepository;

    @Override
    public ProductoEntity findById(Long id) {
        log.info("Procedemos a buscar el producto con id: " + id);
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No pudimos encontrar el producto con ID: " + id
                ));
    }


    @Override
    public ProductoEntity create(ProductoEntity producto) {
        if (producto.getIdProducto() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No se debe enviar ID para crear un producto.");
        }
        log.info("Procedemos a guardar el producto nuevo");
        return productoRepository.save(producto);
    }

    @Override
    public ProductoEntity update(Long id, ProductoEntity producto) {
        productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto con ID " + id + " no existe para actualizar."));
        log.info("Procedemos a actualizar el producto {}", id);
        producto.setIdProducto(id);
        return productoRepository.save(producto);
    }
}
