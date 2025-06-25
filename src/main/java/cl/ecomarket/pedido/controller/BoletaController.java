package cl.ecomarket.pedido.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador tipo REST para la gestión de boletas.
 * <p>
 * Proporciona endpoints para crear y listar boletas en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/boleta")
@Tag(name = "Boletas", description = "Métodos relacionados con las Boletas.")
public class BoletaController {
    
    /**
     * Logger para registrar eventos y errores en la clase BoletaController.
     */
    private final Logger logger = LoggerFactory.getLogger(BoletaController.class);

    /**
     * Servicio para la gestión de boletas.
     */
    @Autowired
    private BoletaService boletaService;

    /**
     * Constructor por defecto para BoletaController.
     * Requerido por Spring para la inyección de dependencias.
     */
    public BoletaController() {
        // Constructor por defecto
    }

    /**
     * Crea una nueva boleta en el sistema.
     *
     * @param boletaDto El DTO de la boleta a crear.
     * @return La boleta creada.
     */
    @PostMapping("/guardar")
    @Operation(summary = "Crea una nueva boleta", description = "Guarda una nueva boleta en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boleta creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public BoletaDto crearBoleta(@RequestBody BoletaDto boletaDto) {
        logger.info("[crearBoleta] Inicio");
        return boletaService.guardarBoleta(boletaDto);
    }

    /**
     * Obtiene una lista de todas las boletas registradas.
     *
     * @return Lista de boletas.
     */
    @GetMapping("/listar")
    @Operation(summary = "Metodo de listado de boletas", description = "Obtiene una lista de todas las boletas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay boletas para mostrar.")
    })
    public List<BoletaDto> listarBoletas() {
        logger.info("[listarBoletas] Inicio");
        return boletaService.listarBoletas();
    }
}
