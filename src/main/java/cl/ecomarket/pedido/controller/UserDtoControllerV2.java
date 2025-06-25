package cl.ecomarket.pedido.controller;

import java.util.Collections;
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

import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.UserServiceDto;
import cl.ecomarket.pedido.assemblers.UserDTOModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * Controlador tipo REST versión 2 para la gestión de usuarios.
 * <p>
 * Proporciona endpoints para listar los usuarios disponibles en el sistema,
 * devolviendo respuestas enriquecidas con HATEOAS.
 * </p>
 */
@RestController
@RequestMapping("/api/v2/user")
@Tag(name = "Usuarios", description = "Métodos relacionados con los usuarios.")
public class UserDtoControllerV2 {

    /**
     * Logger para registrar eventos y errores en la clase UserDtoControllerV2.
     */
    private final Logger logger = LoggerFactory.getLogger(UserDtoControllerV2.class);

    /**
     * Servicio para la gestión de {@link UserDto}.
     */
    @Autowired
    private UserServiceDto userServiceDto;

    /**
     * Ensamblador para convertir {@link UserDto} a EntityModel con enlaces HATEOAS.
     */
    @Autowired
    private UserDTOModelAssembler userModelAssembler;

    /**
     * Constructor por defecto requerido por Spring.
     */
    public UserDtoControllerV2() {
        // Constructor por defecto
    }

    /**
     * Obtiene una lista de todos los usuarios disponibles, cada uno como EntityModel con enlaces HATEOAS.
     *
     * @return CollectionModel de EntityModel de usuarios.
     */
    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "Obtiene una lista de todos los usuarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios para mostrar.")
    })
    public CollectionModel<EntityModel<UserDto>> listarUsuarios() {
        logger.info("[listarUsuarios] Inicio");
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        if (usuarios == null) {
            usuarios = Collections.emptyList();
        }
        if (usuarios.isEmpty()) {
            logger.warn("[listarUsuarios] Fin - No se encontraron usuarios.");
        } else {
            logger.info("[listarUsuarios] Fin - Se encontraron {} usuarios.", usuarios.size());
        }
        List<EntityModel<UserDto>> usuariosModel = usuarios.stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuariosModel,
                linkTo(methodOn(UserDtoController.class).listarUsuarios()).withSelfRel()
        );
    }

    /**
     * Obtiene una lista de todos los usuarios en formato DTO, cada uno como EntityModel con enlaces HATEOAS.
     *
     * @return CollectionModel de EntityModel de usuarios en formato DTO.
     */
    @GetMapping("/listar/dto")
    @Operation(summary = "Listar usuarios DTO", description = "Obtiene una lista de todos los usuarios en formato DTO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado DTO obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios DTO para mostrar.")
    })
    public CollectionModel<EntityModel<UserDto>> listarUsuariosDto() {
        logger.info("[listarUsuariosDto] Inicio");
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        if (usuarios == null) {
            usuarios = Collections.emptyList();
        }
        if (usuarios.isEmpty()) {
            logger.warn("[listarUsuariosDto] Fin - No se encontraron usuarios DTO.");
        } else {
            logger.info("[listarUsuariosDto] Fin - Se encontraron {} usuarios DTO.", usuarios.size());
        }
        List<EntityModel<UserDto>> usuariosModel = usuarios.stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuariosModel,
                linkTo(methodOn(UserDtoController.class).listarUsuariosDto()).withSelfRel()
        );
    }
}