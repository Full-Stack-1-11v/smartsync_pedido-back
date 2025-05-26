package cl.ecomarket.pedido.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.client.UserFeingInterfaz;
import cl.ecomarket.pedido.dto.UserDto;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserServiceDto {
    
   @Autowired
   private UserFeingInterfaz userFeingInterfaz;

    public List<UserDto> listarUsuarios() {
        return userFeingInterfaz.listarUsuarios(); // Retorna una lista de UserDto
    }

    public UserDto obtenerUsuarioPorId(Integer id) {
       return null; // Retorna un UserDto
    }

    public UserDto crearUsuario(UserDto userDto) {
       return null; // Retorna el UserDto creado
    }

    public UserDto actualizarUsuario(Integer id, UserDto userDto) {
       return null; // Retorna el UserDto actualizado
    }
    
}
