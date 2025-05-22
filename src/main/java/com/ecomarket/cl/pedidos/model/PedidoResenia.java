package com.ecomarket.cl.pedidos.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido_resenia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResenia;
    
    @Column(name = "Calificacion", nullable = false)
    private Long calificacion;
    
    @Column(name = "Comentario", nullable = false)
    private String comentario;
    
    @Column(name = "Fecha_resenia", nullable = false)
    private Date fechaResenia;

    @OneToOne
    @JoinColumn(name = "idPedido", nullable = false)
    private Pedido pedido;

}
