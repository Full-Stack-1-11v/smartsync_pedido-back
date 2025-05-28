package cl.ecomarket.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.UserServiceDto;

@RestController
@RequestMapping("/api/v1/user")
public class UserDtoController {
 
    @Autowired
    private UserServiceDto userServiceDto;


    @GetMapping("/listar")
public List<UserDto> listarUsuarios() {
    List<UserDto> usuarios = userServiceDto.listarUsuarios();
    System.out.println("Usuarios recibidos: " + usuarios);
    return usuarios != null ? usuarios : List.of();
}


    @GetMapping("/listar/dto")
    public List<UserDto> listarUsuariosDto() {
        List<UserDto> usuarios = userServiceDto.listarUsuarios();
        System.out.println("Usuarios DTO recibidos: " + usuarios);
        return usuarios != null ? usuarios : List.of();
    }
}
