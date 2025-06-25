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
 * Clase service para gestionar información de pedidos.
 */
@Service
@Transactional
public class PedidoService {

    /**
     * Logger de la clase.
     */
    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);

    /**
     * 
     */
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * 
     * @return
     */
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    /**
     * 
     * @param pedidoId
     * @return
     */
    public Pedido findByPedidoId(Long pedidoId) {
        return pedidoRepository.findByPedidoId(pedidoId);
    }

    /**
     * Metodo que gestiona un envio.
     * 
     * @param pedidoId a gestionar
     * @return un {@link Pedido} si lo encuentra, si no lo encuentra devuelve null
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
     * 
     * @param pedido
     * @return
     */
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
