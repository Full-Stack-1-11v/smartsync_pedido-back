package cl.ecomarket.pedido.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import cl.ecomarket.pedido.dto.UserDto;

/**
 * Interfaz Feign para la comunicación con el servicio de usuarios.
 */
@FeignClient(name = "user-service", url = "https://smartsync-usuario-back-testing.onrender.com")
public interface UserFeingInterfaz {

    /**
     * Método para obtener el listado de usuarios.
     * @return listado de {@link UserDto}
     */
    @GetMapping("/api/v1/user/listar")
    List<UserDto> listarUsuarios();

    /**
     * Método para obtener el listado de usuarios en formato DTO.
     * @return listado de {@link UserDto}
     */
    @GetMapping("/api/v1/user/listar/dto")
    List<UserDto> listarUsuariosDto();

    
}
