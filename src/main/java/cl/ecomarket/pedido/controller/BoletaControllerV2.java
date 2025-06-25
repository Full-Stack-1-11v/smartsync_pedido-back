package cl.ecomarket.pedido.controller;

import java.util.List;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/api/v2/boleta")
@Tag(name = "Boletas", description = "Métodos relacionados con las Boletas.")
public class BoletaControllerV2 {
    
    @Autowired
    private BoletaService boletaService;

    @Autowired
    private BoletaModelAssembler boletaModelAssembler;

    @PostMapping("/guardar")
    @Operation(summary = "Crea una nueva boleta", description = "Guarda una nueva boleta en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Boleta creada exitosamente."),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public EntityModel<BoletaDto> crearBoleta(@RequestBody BoletaDto boletaDto) {
        BoletaDto nuevaBoleta = boletaService.guardarBoleta(boletaDto);
        return boletaModelAssembler.toModel(nuevaBoleta);
    }

    @GetMapping("/listar")
    @Operation(summary = "Metodo de listado de boletas", description = "Obtiene una lista de todas las boletas registradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay boletas para mostrar.")
    })
    public CollectionModel<EntityModel<BoletaDto>> listarBoletas() {
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