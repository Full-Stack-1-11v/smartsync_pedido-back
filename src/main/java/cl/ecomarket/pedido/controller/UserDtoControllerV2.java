package cl.ecomarket.pedido.controller;

import java.util.List;
import java.util.stream.Collectors;

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

@RestController
@RequestMapping("/api/v2/user")
@Tag(name = "Usuarios", description = "Métodos relacionados con los usuarios.")
public class UserDtoControllerV2 {

    @Autowired
    private UserServiceDto userServiceDto;

    @Autowired
    private UserDTOModelAssembler userModelAssembler;

    @GetMapping("/listar")
    @Operation(summary = "Listar usuarios", description = "Obtiene una lista de todos los usuarios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios para mostrar.")
    })
    public CollectionModel<EntityModel<UserDto>> listarUsuarios() {
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        List<EntityModel<UserDto>> usuariosModel = usuarios.stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuariosModel,
                linkTo(methodOn(UserDtoController.class).listarUsuarios()).withSelfRel()
        );
    }

    @GetMapping("/listar/dto")
    @Operation(summary = "Listar usuarios DTO", description = "Obtiene una lista de todos los usuarios en formato DTO.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado DTO obtenido exitosamente."),
        @ApiResponse(responseCode = "204", description = "No hay usuarios DTO para mostrar.")
    })
    public CollectionModel<EntityModel<UserDto>> listarUsuariosDto() {
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        List<EntityModel<UserDto>> usuariosModel = usuarios.stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                usuariosModel,
                linkTo(methodOn(UserDtoController.class).listarUsuariosDto()).withSelfRel()
        );
    }
}