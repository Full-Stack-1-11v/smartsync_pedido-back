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

@RestController
@RequestMapping("/api/v1/boleta")
@Tag(name = "Boletas", description = "Métodos relacionados con las Boletas.")
public class BoletaController {
    
    private final Logger logger = LoggerFactory.getLogger(BoletaController.class);

    @Autowired
    private BoletaService boletaService;

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
