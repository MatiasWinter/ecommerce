package py.com.ecommerce.producto.service;

import py.com.ecommerce.producto.entity.ProductoEntity;

public interface ProductoService {

    ProductoEntity findById(Long id);

    ProductoEntity create(ProductoEntity producto);

    ProductoEntity update(Long id, ProductoEntity producto);
}
