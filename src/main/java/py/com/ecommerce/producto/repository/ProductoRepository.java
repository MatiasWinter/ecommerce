package py.com.ecommerce.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import py.com.ecommerce.producto.entity.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
}
