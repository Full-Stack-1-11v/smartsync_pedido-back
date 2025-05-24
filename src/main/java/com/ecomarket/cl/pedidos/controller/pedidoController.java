package com.ecomarket.cl.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.cl.pedidos.model.Pedido;
import com.ecomarket.cl.pedidos.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

        @GetMapping("/listar")
        public ResponseEntity<List<Pedido>> listarTodos() {
         return ResponseEntity.ok(pedidoService.findAll());
    }   

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        Pedido pedido = pedidoService.findByPedidoId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

     @PutMapping("/{id}")
public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido){
    Pedido existente = pedidoService.findByPedidoId(id);
    if (existente != null) {
        existente.setEstadoPedido(pedido.isEstadoPedido());
        existente.setFechaPedido(pedido.getFechaPedido());
        pedidoService.guardarPedido(existente);
        return ResponseEntity.ok(existente);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}