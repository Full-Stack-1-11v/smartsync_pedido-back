package cl.ecomarket.pedido.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DTO que representa un pedido en el sistema.
 * <p>
 * Contiene la información básica de un pedido, como su identificador, estado y fecha.
 * </p>
 */
@Data
@AllArgsConstructor
public class PedidoDto {

    /**
     * Constructor por defecto requerido por JavaBean y Lombok.
     */
    public PedidoDto() {
        // Constructor por defecto
    }

    /**
     * Identificador único del pedido.
     */
    private Long pedidoId;

    /**
     * Estado del pedido (true si está activo, false si está inactivo o cerrado).
     */
    private boolean estadoPedido;

    /**
     * Fecha en la que se realizó el pedido.
     */
    private Date fechaPedido;

}
