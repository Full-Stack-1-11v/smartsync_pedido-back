package cl.ecomarket.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * DTO que representa un producto en el sistema.
 * <p>
 * Contiene la información básica de un producto, como su identificador, nombre y precio.
 * </p>
 */
@Data
@AllArgsConstructor
public class ProductoDto {

    /**
     * Constructor por defecto requerido por JavaBean y Lombok.
     */
    public ProductoDto() {
        // Constructor por defecto
    }

    /**
     * Identificador único del producto.
     */
    private Long idProducto;

    /**
     * Nombre del producto.
     */
    private String nombreProducto;

    /**
     * Precio del producto.
     */
    private int precioProducto;
}
