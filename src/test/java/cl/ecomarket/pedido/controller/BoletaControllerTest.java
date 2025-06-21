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
import com.fasterxml.jackson.databind.ObjectMapper;
import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.dto.PedidoDto;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.BoletaService;

@WebMvcTest(BoletaController.class)
public class BoletaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearBoleta() throws Exception {
        // Arrange
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boletaDto = new BoletaDto();
        boletaDto.setPedido(pedido);
        boletaDto.setProducto(producto);
        boletaDto.setUser(user);

        when(boletaService.guardarBoleta(any(BoletaDto.class))).thenReturn(boletaDto);

        // Act & Assert
        mockMvc.perform(post("/api/v1/boleta/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boletaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido.pedidoId").value(1))
                .andExpect(jsonPath("$.pedido.estadoPedido").value(true))
                .andExpect(jsonPath("$.producto.idProducto").value(1))
                .andExpect(jsonPath("$.producto.nombreProducto").value("Tomate"))
                .andExpect(jsonPath("$.producto.precioProducto").value(400))
                .andExpect(jsonPath("$.user.id").value(1))
                .andExpect(jsonPath("$.user.name").value("John Doe"))
                .andExpect(jsonPath("$.user.email").value("john.doe@ejemplo.com"));

        verify(boletaService, times(1)).guardarBoleta(any(BoletaDto.class));
    }

    @Test
    public void testListarBoletas() throws Exception {
        // Arrange
        PedidoDto pedido1 = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto1 = new ProductoDto(1L, "Tomate", 400);
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        PedidoDto pedido2 = new PedidoDto(2L, false, java.sql.Date.valueOf("2025-06-06"));
        ProductoDto producto2 = new ProductoDto(2L, "Lechuga", 300);
        UserDto user2 = new UserDto(2, "Jane Doe", "jane.doe@ejemplo.com");

        BoletaDto boleta1 = new BoletaDto();
        boleta1.setPedido(pedido1);
        boleta1.setProducto(producto1);
        boleta1.setUser(user1);

        BoletaDto boleta2 = new BoletaDto();
        boleta2.setPedido(pedido2);
        boleta2.setProducto(producto2);
        boleta2.setUser(user2);

        List<BoletaDto> boletas = Arrays.asList(boleta1, boleta2);

        when(boletaService.listarBoletas()).thenReturn(boletas);

        // Act & Assert
        mockMvc.perform(get("/api/v1/boleta/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].pedido.pedidoId").value(1))
                .andExpect(jsonPath("$[0].pedido.estadoPedido").value(true))
                .andExpect(jsonPath("$[0].producto.idProducto").value(1))
                .andExpect(jsonPath("$[0].producto.nombreProducto").value("Tomate"))
                .andExpect(jsonPath("$[0].producto.precioProducto").value(400))
                .andExpect(jsonPath("$[0].user.id").value(1))
                .andExpect(jsonPath("$[0].user.name").value("John Doe"))
                .andExpect(jsonPath("$[0].user.email").value("john.doe@ejemplo.com"))
                .andExpect(jsonPath("$[1].pedido.pedidoId").value(2))
                .andExpect(jsonPath("$[1].pedido.estadoPedido").value(false))
                .andExpect(jsonPath("$[1].producto.idProducto").value(2))
                .andExpect(jsonPath("$[1].producto.nombreProducto").value("Lechuga"))
                .andExpect(jsonPath("$[1].producto.precioProducto").value(300))
                .andExpect(jsonPath("$[1].user.id").value(2))
                .andExpect(jsonPath("$[1].user.name").value("Jane Doe"))
                .andExpect(jsonPath("$[1].user.email").value("jane.doe@ejemplo.com"));

        verify(boletaService, times(1)).listarBoletas();
    }
}
