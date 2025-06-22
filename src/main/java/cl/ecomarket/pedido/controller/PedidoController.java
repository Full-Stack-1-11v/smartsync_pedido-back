package cl.ecomarket.pedido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Metodos relacionados con los Pedidos.")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listar")
    @Operation(summary = "Buscar todos los Pedidos", description = "Metodo que obtiene una lista con todos los Pedidos existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Obtencion de Pedidos Exitoso!"),
        @ApiResponse(responseCode = "204", description = "Solicitud exitosa, pero no hay contenido que mostrar.")
    })
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }   

    @GetMapping("/{id}/buscar")
    @Operation(summary = "Buscar Pedido por ID", description = "Metodo que busca un Pedido por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente!"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado.")
    })
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        Pedido pedido = pedidoService.findByPedidoId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/guardar")
    @Operation(summary = "Guardar un nuevo Pedido", description = "Metodo que guarda un nuevo Pedido en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente!",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @PutMapping("/{id}/autualizar")
    @Operation(summary = "Actualizar un Pedido existente", description = "Metodo que actualiza los datos de un Pedido existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente!",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado."),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
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

    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Eliminar un Pedido por ID", description = "Metodo que elimina un Pedido por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente!"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        try {
            pedidoService.eliminarPedido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}