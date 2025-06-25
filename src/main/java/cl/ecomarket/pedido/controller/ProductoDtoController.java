package cl.ecomarket.pedido.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador tipo REST para la gestión de productos.
 * <p>
 * Proporciona endpoints para listar los productos disponibles en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/ecomarket/producto")
@Tag(name = "Productos", description = "Métodos relacionados con los productos.")
public class ProductoDtoController {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public ProductoDtoController() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase ProductoDtoController.
     */
    private final Logger logger = LoggerFactory.getLogger(ProductoDtoController.class);

    /**
     * Servicio para la gestión de productos.
     */
    @Autowired
    private ProductoServiceDto productoServiceDto;

    /**
     * Obtiene una lista de todos los productos disponibles.
     *
     * @return Lista de productos.
     */
    @GetMapping()
    @Operation(summary = "Listar productos", description = "Obtiene una lista de todos los productos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay productos para mostrar.")
    })
    public List<ProductoDto> listarProductos() {
        logger.info("[listarProductos] Inicio");
        List<ProductoDto> productos = productoServiceDto.listaList();
        if (productos == null) {
            logger.warn("[listarProductos] Fin - La lista de productos es nula, devolviendo lista vacía.");
            productos = new ArrayList<>();
        } else {
            logger.info("[listarProductos] Fin - Se encontraron {} productos.", productos.size());
        }
        /**
         * Devuelve la lista de productos obtenida del servicio.
         */
        return productos;
    }
}
