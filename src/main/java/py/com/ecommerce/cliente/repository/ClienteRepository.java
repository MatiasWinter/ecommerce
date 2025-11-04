package py.com.ecommerce.cliente.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import py.com.ecommerce.cliente.entity.ClienteEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByEmail(String email);
    List<ClienteEntity> findByEstado(String estado);
}
