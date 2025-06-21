package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import cl.ecomarket.pedido.model.Pedido;
import cl.ecomarket.pedido.service.PedidoService;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void testListarTodos() throws Exception {
        Pedido pedido1 = new Pedido();
        Pedido pedido2 = new Pedido();
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);

        when(pedidoService.findAll()).thenReturn(pedidos);

        mockMvc.perform(get("/api/v1/pedidos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(pedidoService, times(1)).findAll();
    }

    @Test
    public void testBuscarPedidoExistente() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);

        when(pedidoService.findByPedidoId(1L)).thenReturn(pedido);

        mockMvc.perform(get("/api/v1/pedidos/1/buscar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedidoId").value(1L));

        verify(pedidoService, times(1)).findByPedidoId(1L);
    }

    @Test
    public void testBuscarPedidoNoExistente() throws Exception {
        when(pedidoService.findByPedidoId(1L)).thenReturn(null);

        mockMvc.perform(get("/api/v1/pedidos/1/buscar"))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).findByPedidoId(1L);
    }

    @Test
    public void testGuardarPedido() throws Exception {
        Pedido pedido = new Pedido();
        pedido.setPedidoId(1L);

        when(pedidoService.guardarPedido(any(Pedido.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/v1/pedidos/guardar")
                .contentType(MediaType.APPLICATION_JSON) // Especifica el tipo de contenido como JSON
                .content("{\"id\": 1, \"estadoPedido\": true}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pedidoId").value(1L));

        verify(pedidoService, times(1)).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testActualizarPedidoExistente() throws Exception {
        Pedido pedidoExistente = new Pedido();
        pedidoExistente.setPedidoId(1L);

        Pedido pedidoActualizado = new Pedido();
        pedidoActualizado.setEstadoPedido(true);

        when(pedidoService.findByPedidoId(1L)).thenReturn(pedidoExistente);
        when(pedidoService.guardarPedido(any(Pedido.class))).thenReturn(pedidoExistente);

        mockMvc.perform(put("/api/v1/pedidos/1/autualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"estadoPedido\": true}"))
                .andExpect(status().isOk());

        verify(pedidoService, times(1)).findByPedidoId(1L);
        verify(pedidoService, times(1)).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testActualizarPedidoNoExistente() throws Exception {
        when(pedidoService.findByPedidoId(1L)).thenReturn(null);

        mockMvc.perform(put("/api/v1/pedidos/1/autualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"estadoPedido\": true}"))
                .andExpect(status().isNotFound());

        verify(pedidoService, times(1)).findByPedidoId(1L);
        verify(pedidoService, never()).guardarPedido(any(Pedido.class));
    }

    @Test
    public void testEliminarPedidoExistente() throws Exception {
        doNothing().when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/v1/pedidos/1/eliminar"))
                .andExpect(status().isNoContent());

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }

    @Test
    public void testEliminarPedidoConError() throws Exception {
        doThrow(new RuntimeException("Error al eliminar")).when(pedidoService).eliminarPedido(1L);

        mockMvc.perform(delete("/api/v1/pedidos/1/eliminar"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error al eliminar"));

        verify(pedidoService, times(1)).eliminarPedido(1L);
    }
}
