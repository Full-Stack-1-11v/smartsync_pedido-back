package cl.ecomarket.pedido.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.pedido.client.UserFeingInterfaz;
import cl.ecomarket.pedido.dto.UserDto;
import jakarta.transaction.Transactional;

/**
 * Clase service para la gestión de usuarios a través de Feign Client.
 * <p>
 * Permite obtener la lista de usuarios disponibles
 * consultando el microservicio de usuarios.
 * </p>
 */
@Transactional
@Service
public class UserServiceDto {

    /**
     * Constructor por defecto requerido por Spring.
     */
    public UserServiceDto() {
        // Constructor por defecto
    }

    /**
     * Logger para registrar eventos y errores en la clase UserServiceDto.
     */
    private final Logger logger = LoggerFactory.getLogger(UserServiceDto.class);

    /**
     * Cliente Feign para consumir el microservicio de usuarios.
     */
    @Autowired
    private UserFeingInterfaz userFeingInterfaz;

    /**
     * Obtiene la lista de usuarios disponibles
     * desde el microservicio de usuarios.
     *
     * @return Lista de {@link UserDto}.
     */
    public List<UserDto> listarUsuarios() {
        logger.info("[listarUsuarios] Inicio - Consultando usuarios al microservicio de usuarios");
        List<UserDto> usuarios = userFeingInterfaz.listarUsuarios();
        if (usuarios == null || usuarios.isEmpty()) {
            logger.warn("[listarUsuarios] Fin - No se encontraron usuarios.");
        } else {
            logger.info("[listarUsuarios] Fin - Se encontraron {} usuarios.", usuarios.size());
        }
        return usuarios;
    }
}
