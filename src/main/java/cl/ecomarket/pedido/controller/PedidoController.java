package cl.ecomarket.pedido.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Controlador tipo REST para la gestión de pedidos.
 * <p>
 * Proporciona endpoints para crear, listar, buscar, actualizar y eliminar pedidos en el sistema.
 * </p>
 */
@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Metodos relacionados con los Pedidos.")
public class PedidoController {

    /**
     * Logger para registrar eventos y errores en la clase PedidoController.
     */
    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    /**
     * Servicio para la gestión de pedidos.
     */
    @Autowired
    private PedidoService pedidoService;

    /**
     * Constructor por defecto requerido por Spring.
     */
    public PedidoController() {
        // Constructor por defecto
    }

    /**
     * Obtiene una lista con todos los pedidos existentes.
     *
     * @return ResponseEntity con la lista de pedidos.
     */
    @GetMapping("/listar")
    @Operation(summary = "Buscar todos los Pedidos", description = "Metodo que obtiene una lista con todos los Pedidos existentes.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Obtencion de Pedidos Exitoso!"),
        @ApiResponse(responseCode = "204", description = "Solicitud exitosa, pero no hay contenido que mostrar.")
    })
    public ResponseEntity<List<Pedido>> listarTodos() {
        logger.info("[listarTodos] Inicio");
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Busca un pedido por su ID.
     *
     * @param id El ID del pedido a buscar.
     * @return ResponseEntity con el pedido encontrado o 404 si no existe.
     */
    @GetMapping("/{id}/buscar")
    @Operation(summary = "Buscar Pedido por ID", description = "Metodo que busca un Pedido por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado exitosamente!"),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado.")
    })
    public ResponseEntity<Pedido> buscar(@PathVariable Long id){
        logger.info("[buscar] Buscando pedido con ID: {}", id);
        Pedido pedido = pedidoService.findByPedidoId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        } else {
            logger.warn("[buscar] Pedido con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Guarda un nuevo pedido en el sistema.
     *
     * @param pedido El pedido a guardar.
     * @return ResponseEntity con el pedido creado.
     */
    @PostMapping("/guardar")
    @Operation(summary = "Guardar un nuevo Pedido", description = "Metodo que guarda un nuevo Pedido en el sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente!",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public ResponseEntity<Pedido> guardar(@RequestBody Pedido pedido){
        logger.info("[guardar] Inicio - Guardando nuevo pedido");
        Pedido nuevoPedido = pedidoService.guardarPedido(pedido);
        logger.info("[guardar] Fin - Nuevo pedido guardado con ID: {}", nuevoPedido.getPedidoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    /**
     * Actualiza los datos de un pedido existente.
     *
     * @param id El ID del pedido a actualizar.
     * @param pedido Los nuevos datos del pedido.
     * @return ResponseEntity con el pedido actualizado o 404 si no existe.
     */
    @PutMapping("/{id}/actualizar")
    @Operation(summary = "Actualizar un Pedido existente", description = "Metodo que actualiza los datos de un Pedido existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido actualizado exitosamente!",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Pedido.class))),
        @ApiResponse(responseCode = "404", description = "Pedido no encontrado."),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido){
        logger.info("[actualizar] Inicio - Actualizando pedido con ID: {}", id);
        Pedido existente = pedidoService.findByPedidoId(id);
        if (existente != null) {
            existente.setEstadoPedido(pedido.isEstadoPedido());
            existente.setFechaPedido(pedido.getFechaPedido());
            pedidoService.guardarPedido(existente);
            logger.info("[actualizar] Fin - Pedido con ID: {} actualizado exitosamente", id);
            return ResponseEntity.ok(existente);
        } else {
            logger.warn("[actualizar] Fin - Pedido con ID {} no encontrado", id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un pedido por su ID.
     *
     * @param id El ID del pedido a eliminar.
     * @return ResponseEntity con el estado de la eliminación.
     */
    @DeleteMapping("/{id}/eliminar")
    @Operation(summary = "Eliminar un Pedido por ID", description = "Metodo que elimina un Pedido por su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Pedido eliminado exitosamente!"),
        @ApiResponse(responseCode = "400", description = "Error en la solicitud.")
    })
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        logger.info("[eliminar] Inicio - Eliminando pedido con ID: {}", id);
        try {
            pedidoService.eliminarPedido(id);
            logger.info("[eliminar] Fin - Pedido con ID: {} eliminado exitosamente", id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("[eliminar] Fin - Error al eliminar pedido con ID: {}. Error: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}