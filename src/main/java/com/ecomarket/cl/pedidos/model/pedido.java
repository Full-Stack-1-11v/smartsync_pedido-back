package com.ecomarket.cl.pedidos.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedido")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedidoId;

    @Column(nullable = false)
    private user usuario;

    @Column(nullable = false)
    private boolean estadoPedido;

    @Column(nullable = false)
    private Date fechaPedido;   
}
