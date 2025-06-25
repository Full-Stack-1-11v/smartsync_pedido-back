package cl.ecomarket.pedido.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.repository.PedidoRepository;
import jakarta.transaction.Transactional;

/**
 * Clase service para la gestión de información de pedidos.
 * <p>
 * Permite realizar operaciones CRUD y gestionar el envío de pedidos.
 * </p>
 */
@Service
@Transactional
public class PedidoService {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public PedidoService() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase PedidoService.
     */
    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    /**
     * Repositorio para acceder a los datos de pedidos.
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Obtiene la lista de todos los pedidos almacenados.
     *
     * @return Lista de pedidos.
     */
    public List<Pedido> findAll() {
        logger.info("[findAll] Inicio - Listando todos los pedidos");
        return pedidoRepository.findAll();
    }

    /**
     * Busca un {@link Pedido} por su identificador único.
     *
     * @param pedidoId El identificador del pedido a buscar.
     * @return El pedido encontrado, o null si no existe.
     */
    public Pedido findByPedidoId(Long pedidoId) {
        logger.info("[findByPedidoId] Inicio - Buscando pedido con ID: {}", pedidoId);
        return pedidoRepository.findByPedidoId(pedidoId);
    }

    /**
     * Gestiona el envío de un {@link Pedido}, cambiando su estado a enviado (activo).
     *
     * @param pedidoId El identificador del pedido a gestionar.
     * @return El pedido actualizado si se encuentra, o null si no existe.
     */
    public Pedido gestionarEnvio(Long pedidoId) {
        logger.info("[gestionarEnvio] Inicio");

        Pedido pedido = pedidoRepository.findByPedidoId(pedidoId);
        if (pedido != null) {
            pedido.setEstadoPedido(true);
            pedidoRepository.save(pedido);

            logger.debug("[gestionarEnvio] Pedido encontrado {}", pedido);
            logger.info("[gestionarEnvio] Fin");
            return pedido;
        } else {
            logger.info("[gestionarEnvio] Fin, pedido no encontrado");
            return null;
        }
    }

    /**
     * Guarda un {@link Pedido} en la base de datos.
     *
     * @param pedido El pedido a guardar.
     * @return El pedido guardado.
     */
    public Pedido guardarPedido(Pedido pedido) {
        logger.info("[guardarPedido] Inicio - Guardando pedido...");
        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        logger.info("[guardarPedido] Fin - Pedido guardado con ID: {}", pedidoGuardado.getPedidoId());
        return pedidoGuardado;
    }

    /**
     * Elimina un {@link Pedido} por su identificador.
     *
     * @param id El identificador del pedido a eliminar.
     */
    public void eliminarPedido(Long id) {
        logger.info("[eliminarPedido] Inicio - Eliminando pedido con ID: {}", id);
        pedidoRepository.deleteById(id);
        logger.info("[eliminarPedido] Fin - Pedido eliminado con ID: {}", id);
    }
}
