package cl.ecomarket.pedido.controller;

import java.util.List;

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

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "Usuarios", description = "Métodos relacionados con los usuarios.")
public class UserDtoController {
 
    @Autowired
    private UserServiceDto userServiceDto;

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "Obtiene una lista de todos los usuarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios para mostrar.")
    })
    public List<UserDto> listarUsuarios() {
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        System.out.println("Usuarios recibidos: " + usuarios);
        return usuarios != null ? usuarios : List.of();
    }

    @GetMapping("/listar/dto")
    @Operation(summary = "Listar usuarios DTO", description = "Obtiene una lista de todos los usuarios en formato DTO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado DTO obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios DTO para mostrar.")
    })
    public List<UserDto> listarUsuariosDto() {
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        System.out.println("Usuarios DTO recibidos: " + usuarios);
        return usuarios != null ? usuarios : List.of();
    }
}
