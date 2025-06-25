package cl.ecomarket.pedido.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.service.BoletaService;
import cl.ecomarket.pedido.assemblers.BoletaModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador tipo REST versión 2 para la gestión de boletas.
 * <p>
 * Proporciona endpoints para crear y listar boletas en el sistema,
 * devolviendo respuestas enriquecidas con HATEOAS.
 * </p>
 */
@RestController
@RequestMapping("/api/v2/boleta")
@Tag(name = "Boletas", description = "Métodos relacionados con las Boletas.")
public class BoletaControllerV2 {

    /**
     * Logger para registrar eventos y errores en la clase BoletaControllerV2.
     */
    private final Logger logger = LoggerFactory.getLogger(BoletaControllerV2.class);

    /**
     * Servicio para la gestión de boletas.
     */
    @Autowired
    private BoletaService boletaService;

    /**
     * Ensamblador que convierte un {@link BoletaDto} a EntityModel con enlaces HATEOAS.
     */
    @Autowired
    private BoletaModelAssembler boletaModelAssembler;

    /**
     * Constructor por defecto requerido por Spring 
     * para la inyección de dependencias.
     */
    public BoletaControllerV2() {
        // Constructor por defecto
    }

    /**
     * Crea una nueva {@link BoletaDto} en el sistema y la retorna como EntityModel con enlaces HATEOAS.
     *
     * @param boletaDto El DTO de la boleta a crear.
     * @return La boleta creada envuelta en un EntityModel con enlaces HATEOAS.
     */
    @PostMapping("/guardar")
    @Operation(summary = "Crea una nueva boleta", description = "Guarda una nueva boleta en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boleta creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public EntityModel<BoletaDto> crearBoleta(@RequestBody BoletaDto boletaDto) {
        logger.info("[crearBoleta] Inicio");
        BoletaDto nuevaBoleta = boletaService.guardarBoleta(boletaDto);
        return boletaModelAssembler.toModel(nuevaBoleta);
    }

    /**
     * Obtiene una lista de todas las boletas registradas, cada una como EntityModel con enlaces HATEOAS.
     *
     * @return Colección de EntityModel de boletas con enlaces HATEOAS.
     */
    @GetMapping("/listar")
    @Operation(summary = "Metodo de listado de boletas", description = "Obtiene una lista de todas las boletas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay boletas para mostrar.")
    })
    public CollectionModel<EntityModel<BoletaDto>> listarBoletas() {
        logger.info("[listarBoletas] Inicio");
        List<BoletaDto> boletas = boletaService.listarBoletas();
        List<EntityModel<BoletaDto>> boletasModel = boletas.stream()
                .map(boletaModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                boletasModel,
                linkTo(methodOn(BoletaController.class).listarBoletas()).withSelfRel()
        );
    }
}