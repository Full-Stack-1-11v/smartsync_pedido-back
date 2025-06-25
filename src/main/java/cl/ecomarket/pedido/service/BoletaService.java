package cl.ecomarket.pedido.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.dto.BoletaDto;

import jakarta.transaction.Transactional;

/**
 * Clase service para la gestión de boletas en el sistema.
 * <p>
 * Permite guardar y listar boletas utilizando una lista en memoria.
 * </p>
 */
@Transactional
@Service
public class BoletaService {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public BoletaService() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase BoletaService.
     */
    private final Logger logger = LoggerFactory.getLogger(BoletaService.class);

    /**
     * Lista en memoria que almacena las boletas.
     */
    private final List<BoletaDto> boletas = new ArrayList<>();

    /**
     * Guarda una nueva boleta en la lista.
     *
     * @param boleta La boleta a guardar.
     * @return La boleta guardada.
     */
    public BoletaDto guardarBoleta(BoletaDto boleta) {
        logger.info("[guardarBoleta] Inicio - Guardando boleta");
        boletas.add(boleta);
        logger.info("[guardarBoleta] Fin - Boleta guardada correctamente. Total boletas: {}", boletas.size());
        return boleta;
    }

    /**
     * Obtiene la lista de todas las boletas almacenadas.
     *
     * @return Lista de boletas.
     */
    public List<BoletaDto> listarBoletas() {
        logger.info("[listarBoletas] Inicio - Listando boletas. Total boletas: {}", boletas.size());
        return boletas;
    }
}


