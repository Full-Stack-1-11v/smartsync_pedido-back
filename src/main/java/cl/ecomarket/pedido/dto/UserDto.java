package cl.ecomarket.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO que representa un usuario en el sistema.
 * <p>
 * Contiene la información básica de un usuario, como su identificador, nombre y correo electrónico.
 * </p>
 */
@Data
@AllArgsConstructor

public class UserDto {

    /**
     * Constructor por defecto requerido por JavaBean y Lombok.
     */
    public UserDto() {
        // Constructor por defecto
    }

    /**
     * Identificador único del usuario.
     */
    private Integer id;

    /**
     * Nombre del usuario.
     */
    private String name;

    /**
     * Correo electrónico del usuario.
     */
    private String email;
}
