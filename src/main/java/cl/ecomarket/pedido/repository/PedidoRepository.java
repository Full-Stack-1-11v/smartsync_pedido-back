package cl.ecomarket.pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.ecomarket.pedido.model.Pedido;

/**
 * Repositorio para la entidad {@link Pedido}.
 * <p>
 * Proporciona métodos para realizar operaciones CRUD y consultas personalizadas sobre la tabla de pedidos.
 * </p>
 * 
 * @author TuNombre
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    /**
     * Busca un pedido por su identificador único.
     *
     * @param pedidoId el identificador del pedido a buscar.
     * @return el pedido encontrado, o null si no existe.
     */
    Pedido findByPedidoId(Long pedidoId);
}
