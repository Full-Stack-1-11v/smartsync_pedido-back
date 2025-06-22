package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;

@WebMvcTest(ProductoDtoController.class)
public class ProductoDtoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServiceDto productoServiceDto;

    @Test
    public void testListarProductos() throws Exception {
        // Mock de datos
        List<ProductoDto> mockProductos = List.of(
            new ProductoDto(1L, "Lechuga", 300),
            new ProductoDto(2L, "Tomate", 500)
        );

        // Simular el comportamiento del servicio
        when(productoServiceDto.listaList()).thenReturn(mockProductos);

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(get("/api/v1/ecomarket/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(mockProductos.size()))
                .andExpect(jsonPath("$[0].idProducto").value(mockProductos.get(0).getIdProducto()))
                .andExpect(jsonPath("$[0].nombreProducto").value(mockProductos.get(0).getNombreProducto()))
                .andExpect(jsonPath("$[0].precioProducto").value(mockProductos.get(0).getPrecioProducto()))
                .andExpect(jsonPath("$[1].idProducto").value(mockProductos.get(1).getIdProducto()))
                .andExpect(jsonPath("$[1].nombreProducto").value(mockProductos.get(1).getNombreProducto()))
                .andExpect(jsonPath("$[1].precioProducto").value(mockProductos.get(1).getPrecioProducto()));
    }

    @Test
    public void testListarProductosNull() throws Exception {
        // Simular el caso en el que el servicio devuelve null
        when(productoServiceDto.listaList()).thenReturn(null);

        // Realizar la solicitud y verificar que se devuelve una lista vacía
        mockMvc.perform(get("/api/v1/ecomarket/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0)); // Verificar que la lista es vacía
    }
}
