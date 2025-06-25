package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import cl.ecomarket.pedido.assemblers.PedidoModelAssembler;
import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.service.PedidoService;

@WebMvcTest(PedidoControllerV2.class)
public class PedidoControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @MockBean
    private PedidoModelAssembler pedidoModelAssembler; // <-- ¡Agrega esto!

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testListarTodosHateoas() throws Exception {
        Pedido pedido1 = new Pedido();
        pedido1.setPedidoId(1L);
        pedido1.setEstadoPedido(true);
        pedido1.setFechaPedido(Date.valueOf("2025-06-05"));

        Pedido pedido2 = new Pedido();
        pedido2.setPedidoId(2L);
        pedido2.setEstadoPedido(false);
        pedido2.setFechaPedido(Date.valueOf("2025-06-06"));

        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoService.findAll()).thenReturn(pedidos);

        // Mock del assembler para cada pedido
        Link listarLink = Link.of("/api/v2/pedidos/listar").withRel("listar");
        when(pedidoModelAssembler.toModel(pedido1)).thenReturn(EntityModel.of(pedido1, listarLink));
        when(pedidoModelAssembler.toModel(pedido2)).thenReturn(EntityModel.of(pedido2, listarLink));

        mockMvc.perform(get("/api/v2/pedidos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.pedidoList").isArray())
                .andExpect(jsonPath("$._embedded.pedidoList[0].pedidoId").value(1))
                .andExpect(jsonPath("$._embedded.pedidoList[0]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.pedidoList[1].pedidoId").value(2))
                .andExpect(jsonPath("$._embedded.pedidoList[1]._links.listar").exists())
                .andExpect(jsonPath("$._links.self").exists());

        verify(pedidoService, times(1)).findAll();
    }

    @Test
    public void testListarTodosHateoasVacio() throws Exception {
        when(pedidoService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v2/pedidos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist());

        verify(pedidoService, times(1)).findAll();
    }

    @Test
    public void testBuscarPedidoExistenteHateoas() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstadoPedido(true);
        pedido.setFechaPedido(Date.valueOf("2025-06-05"));

        when(pedidoService.findByPedidoId(1L)).thenReturn(pedido);

        // Mock del assembler para el pedido individual
        Link listarLink = Link.of("/api/v2/pedidos/listar").withRel("listar");
        Link buscarLink = Link.of("/api/v2/pedidos/1/buscar").withRel("buscar");
        EntityModel<Pedido> entityModel = EntityModel.of(pedido, listarLink, buscarLink);
        when(pedidoModelAssembler.toModel(pedido)).thenReturn(entityModel);

        mockMvc.perform(get("/api/v2/pedidos/1/buscar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId").value(1))
                .andExpect(jsonPath("$._links.listar").exists())
                .andExpect(jsonPath("$._links.buscar").exists());

        verify(pedidoService, times(1)).findByPedidoId(1L);
    }

    @Test
    public void testBuscarPedidoNoExistenteHateoas() throws Exception {
        when(pedidoService.findByPedidoId(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v2/pedidos/1/buscar"))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).findByPedidoId(1L);
    }

    @Test
    public void testGuardarPedidoHateoas() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);
        pedido.setEstadoPedido(true);
        pedido.setFechaPedido(Date.valueOf("2025-06-05"));

        when(pedidoService.guardarPedido(any(Pedido.class))).thenReturn(pedido);

        // Mock del assembler con los links esperados
        Link listarLink = Link.of("/api/v2/pedidos/listar").withRel("listar");
        Link guardarLink = Link.of("/api/v2/pedidos/guardar").withRel("guardar");
        EntityModel<Pedido> entityModel = EntityModel.of(pedido, listarLink, guardarLink);
        when(pedidoModelAssembler.toModel(pedido)).thenReturn(entityModel);

        mockMvc.perform(post("/api/v2/pedidos/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pedidoId").value(1))
                .andExpect(jsonPath("$._links.listar").exists())
                .andExpect(jsonPath("$._links.guardar").exists());

        verify(pedidoService, times(1)).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testActualizarPedidoExistenteHateoas() throws Exception {
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setPedidoId(1L);
        pedidoExistente.setEstadoPedido(false);
        pedidoExistente.setFechaPedido(Date.valueOf("2025-06-05"));

        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setEstadoPedido(true);
        pedidoActualizado.setFechaPedido(Date.valueOf("2025-06-10"));

        when(pedidoService.findByPedidoId(1L)).thenReturn(pedidoExistente);
        when(pedidoService.guardarPedido(any(Pedido.class))).thenReturn(pedidoExistente);

        // Mock del assembler para el pedido actualizado
        Link listarLink = Link.of("/api/v2/pedidos/listar").withRel("listar");
        Link actualizarLink = Link.of("/api/v2/pedidos/1/autualizar").withRel("actualizar");
        EntityModel<Pedido> entityModel = EntityModel.of(pedidoExistente, listarLink, actualizarLink);
        when(pedidoModelAssembler.toModel(pedidoExistente)).thenReturn(entityModel);

        mockMvc.perform(put("/api/v2/pedidos/1/autualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId").value(1))
                .andExpect(jsonPath("$._links.listar").exists())
                .andExpect(jsonPath("$._links.actualizar").exists());

        verify(pedidoService, times(1)).findByPedidoId(1L);
        verify(pedidoService, times(1)).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testActualizarPedidoNoExistenteHateoas() throws Exception {
        when(pedidoService.findByPedidoId(1L)).thenReturn(null);

        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setEstadoPedido(true);

        mockMvc.perform(put("/api/v2/pedidos/1/autualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedidoActualizado)))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).findByPedidoId(1L);
        verify(pedidoService, never()).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testEliminarPedidoExistenteHateoas() throws Exception {
        doNothing().when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/v2/pedidos/1/eliminar"))
                .andExpect(status().isNoContent());

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }

    @Test
    public void testEliminarPedidoConErrorHateoas() throws Exception {
        doThrow(new RuntimeException("Error al eliminar")).when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/v2/pedidos/1/eliminar"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error al eliminar"));

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }
}
