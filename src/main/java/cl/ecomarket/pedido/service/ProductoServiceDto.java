package cl.ecomarket.pedido.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.client.ProductoFeingInterfaz;
import cl.ecomarket.pedido.dto.ProductoDto;
import jakarta.transaction.Transactional;

/**
 * Clase service para la gestión de productos a través de Feign Client.
 * <p>
 * Permite obtener la lista de productos disponibles
 * consultando el microservicio de productos.
 * </p>
 */
@Transactional
@Service
public class ProductoServiceDto {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public ProductoServiceDto() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase ProductoServiceDto.
     */
    private final Logger logger = LoggerFactory.getLogger(ProductoServiceDto.class);

    /**
     * Cliente Feign para consumir el microservicio de productos.
     */
    @Autowired
    private ProductoFeingInterfaz productoFeingInterfaz;

    /**
     * Obtiene la lista de productos disponibles
     * desde el microservicio de productos.
     *
     * @return Lista de {@link ProductoDto}.
     */
    public List<ProductoDto> listaList() {
        logger.info("[listaList] Inicio - Consultando productos al microservicio de productos");
        List<ProductoDto> productos = productoFeingInterfaz.listarProductos();
        if (productos == null || productos.isEmpty()) {
            logger.warn("[listaList] Fin - No se encontraron productos.");
        } else {
            logger.info("[listaList] Fin - Se encontraron {} productos.", productos.size());
        }
        return productos;
    }
}
