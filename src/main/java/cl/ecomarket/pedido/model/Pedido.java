package cl.ecomarket.pedido.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase que representa la entidad Pedido en el sistema.
 * <p>
 * Esta clase mapea la tabla "pedido" de la base de datos y contiene la información
 * básica de un pedido, como su identificador, estado y fecha.
 * </p>
 */
@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor

public class Pedido {
    
    /**
     * Constructor por defecto requerido por JPA y Lombok.
     */
    public Pedido() {
        // Constructor por defecto
    }

    /**
     * Identificador único del pedido (autogenerado en la base de datos).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pedidoId;

    /**
     * Estado del pedido (true si está activo, false si está inactivo o cerrado).
     * Este campo es obligatorio y no puede ser nulo.
     */
    @Column(name = "estado_pedido", nullable = false)
    private boolean estadoPedido;

    /**
     * Fecha en la que se realizó el pedido.
     * Este campo es obligatorio y no puede ser nulo.
     */
    @Column(name = "fecha_pedido", nullable = false)
    private Date fechaPedido;   
}