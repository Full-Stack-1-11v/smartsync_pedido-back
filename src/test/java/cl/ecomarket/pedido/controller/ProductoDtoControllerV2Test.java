package cl.ecomarket.pedido.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.EntityModel;

import cl.ecomarket.pedido.assemblers.ProductoDTOModelAssembler;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.service.ProductoServiceDto;


@WebMvcTest(ProductoDtoControllerV2.class)
public class ProductoDtoControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductoServiceDto productoServiceDto;

    @MockBean
    private ProductoDTOModelAssembler productoDTOModelAssembler;

    @Test
    public void testListarProductosHateoas() throws Exception {
        ProductoDto producto1 = new ProductoDto(1L, "Tomate", 400);
        ProductoDto producto2 = new ProductoDto(2L, "Lechuga", 300);

        List<ProductoDto> productos = Arrays.asList(producto1, producto2);

        when(productoServiceDto.listaList()).thenReturn(productos);

        // Mock del assembler para cada producto
        Link listarLink = Link.of("/api/v1/ecomarket/producto").withRel("listar");
        when(productoDTOModelAssembler.toModel(producto1)).thenReturn(EntityModel.of(producto1, listarLink));
        when(productoDTOModelAssembler.toModel(producto2)).thenReturn(EntityModel.of(producto2, listarLink));

        mockMvc.perform(get("/api/v1/ecomarket/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.productoDtoList").isArray())
                .andExpect(jsonPath("$._embedded.productoDtoList[0].idProducto").value(1))
                .andExpect(jsonPath("$._embedded.productoDtoList[0].nombreProducto").value("Tomate"))
                .andExpect(jsonPath("$._embedded.productoDtoList[0].precioProducto").value(400))
                .andExpect(jsonPath("$._embedded.productoDtoList[0]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.productoDtoList[1].idProducto").value(2))
                .andExpect(jsonPath("$._embedded.productoDtoList[1].nombreProducto").value("Lechuga"))
                .andExpect(jsonPath("$._embedded.productoDtoList[1].precioProducto").value(300))
                .andExpect(jsonPath("$._embedded.productoDtoList[1]._links.listar").exists())
                .andExpect(jsonPath("$._links.self").exists());

        verify(productoServiceDto, times(1)).listaList();
    }

    @Test
    public void testListarProductosHateoasVacio() throws Exception {
        when(productoServiceDto.listaList()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/v1/ecomarket/producto"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist());

        verify(productoServiceDto, times(1)).listaList();
    }
}
