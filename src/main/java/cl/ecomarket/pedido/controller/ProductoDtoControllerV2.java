package cl.ecomarket.pedido.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.assemblers.ProductoDTOModelAssembler;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador tipo REST versión 2 para la gestión de productos.
 * <p>
 * Proporciona endpoints para listar los productos disponibles en el sistema,
 * devolviendo respuestas enriquecidas con HATEOAS.
 * </p>
 */
@RestController
@RequestMapping("/api/v2/ecomarket/producto")
@Tag(name = "Productos", description = "Métodos relacionados con los productos.")
public class ProductoDtoControllerV2 {

    /**
     * Logger para registrar eventos y errores en la clase ProductoDtoControllerV2.
     */
    private final Logger logger = LoggerFactory.getLogger(ProductoDtoControllerV2.class);

    /**
     * Servicio para la gestión de productos.
     */
    @Autowired
    private ProductoServiceDto productoServiceDto;

    /**
     * Ensamblador que convierte un {@link ProductoDto} a EntityModel con enlaces HATEOAS.
     */
    @Autowired
    private ProductoDTOModelAssembler productoDTOModelAssembler;

    /**
     * Constructor por defecto requerido por Spring.
     */
    public ProductoDtoControllerV2() {
        // Constructor por defecto
    }

    /**
     * Obtiene una lista de todos los productos disponibles, cada uno como EntityModel con enlaces HATEOAS.
     *
     * @return CollectionModel de EntityModel de productos.
     */
    @GetMapping()
    @Operation(summary = "Listar productos", description = "Obtiene una lista de todos los productos disponibles.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay productos para mostrar.")
    })
    public CollectionModel<EntityModel<ProductoDto>> listarProductos() {
        logger.info("[listarProductos] Inicio");
        List<ProductoDto> productos = productoServiceDto.listaList();
        if (productos == null) {
            logger.warn("[listarProductos] Fin - La lista de productos es nula, devolviendo lista vacía.");
            productos = new ArrayList<>();
        } else {
            logger.info("[listarProductos] Fin -Se encontraron {} productos.", productos.size());
        }
        List<EntityModel<ProductoDto>> productosModel = productos.stream()
                .map(productoDTOModelAssembler::toModel)
                .collect(Collectors.toList());

        /**
         * Devuelve la colección de productos con enlaces HATEOAS.
         */
        return CollectionModel.of(
                productosModel,
                linkTo(methodOn(ProductoDtoController.class).listarProductos()).withSelfRel()
        );
    }
}

