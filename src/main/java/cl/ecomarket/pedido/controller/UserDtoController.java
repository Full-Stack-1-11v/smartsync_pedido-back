package cl.ecomarket.pedido.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.UserServiceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador tipo REST para la gestión de usuarios.
 * <p>
 * Proporciona endpoints para listar los usuarios disponibles en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuarios", description = "Métodos relacionados con los usuarios.")
public class UserDtoController {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public UserDtoController() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase UserDtoController.
     */
    private final Logger logger = LoggerFactory.getLogger(UserDtoController.class);

    /**
     * Servicio para la gestión de usuarios.
     */
    @Autowired
    private UserServiceDto userServiceDto;

    /**
     * Obtiene una lista de todos los usuarios disponibles.
     *
     * @return Lista de usuarios.
     */
    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "Obtiene una lista de todos los usuarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios para mostrar.")
    })
    public List<UserDto> listarUsuarios() {
        logger.info("[listarUsuarios] Inicio");
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            logger.warn("[listarUsuarios] Fin - No se encontraron usuarios.");
        } else {
            logger.info("[listarUsuarios] Fin - Se encontraron {} usuarios.", usuarios.size());
        }
        return usuarios;
    }

    /**
     * Obtiene una lista de todos los usuarios en formato DTO.
     *
     * @return Lista de usuarios en formato DTO.
     */
    @GetMapping("/listar/dto")
    @Operation(summary = "Listar usuarios DTO", description = "Obtiene una lista de todos los usuarios en formato DTO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado DTO obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios DTO para mostrar.")
    })
    public List<UserDto> listarUsuariosDto() {
        logger.info("[listarUsuariosDto] Inicio");
        List<UserDto> usuariosDto = userServiceDto.listarUsuarios();
        if (usuariosDto == null || usuariosDto.isEmpty()) {
            logger.warn("[listarUsuariosDto] Fin - No se encontraron usuarios DTO.");
        } else {
            logger.info("[listarUsuariosDto] Fin - Se encontraron {} usuarios DTO.", usuariosDto.size());
        }
        return usuariosDto;
    }
}