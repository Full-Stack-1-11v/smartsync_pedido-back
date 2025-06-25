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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.ecomarket.pedido.assemblers.BoletaModelAssembler;
import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.dto.PedidoDto;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.dto.UserDto;
import cl.ecomarket.pedido.service.BoletaService;

@WebMvcTest(BoletaControllerV2.class)
public class BoletaControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoletaService boletaService;

    @MockBean
    private BoletaModelAssembler boletaModelAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCrearBoletaHateoas() throws Exception {
        // Arrange
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");
        BoletaDto boletaDto = new BoletaDto();
        boletaDto.setPedido(pedido);
        boletaDto.setProducto(producto);
        boletaDto.setUser(user);

        when(boletaService.guardarBoleta(any(BoletaDto.class))).thenReturn(boletaDto);

        // Mock del assembler con links
        Link listarLink = Link.of("/api/v2/boleta/listar").withRel("listar");
        Link guardarLink = Link.of("/api/v2/boleta/guardar").withRel("guardar");
        EntityModel<BoletaDto> entityModel = EntityModel.of(boletaDto, listarLink, guardarLink);

        when(boletaModelAssembler.toModel(any(BoletaDto.class))).thenReturn(entityModel);

        // Act & Assert
        mockMvc.perform(post("/api/v2/boleta/guardar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(boletaDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pedido.pedidoId").value(1))
                .andExpect(jsonPath("$._links.listar").exists())
                .andExpect(jsonPath("$._links.guardar").exists());
    }

    @Test
    public void testListarBoletasHateoas() throws Exception {
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

        // Mock del assembler con links
        Link listarLink = Link.of("/api/v2/boleta/listar").withRel("listar");
        when(boletaModelAssembler.toModel(boleta1)).thenReturn(EntityModel.of(boleta1, listarLink));
        when(boletaModelAssembler.toModel(boleta2)).thenReturn(EntityModel.of(boleta2, listarLink));

        // Act & Assert
        mockMvc.perform(get("/api/v2/boleta/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.boletaDtoList").isArray())
                .andExpect(jsonPath("$._embedded.boletaDtoList[0].pedido.pedidoId").value(1))
                .andExpect(jsonPath("$._embedded.boletaDtoList[0]._links.listar").exists())
                .andExpect(jsonPath("$._embedded.boletaDtoList[1].pedido.pedidoId").value(2))
                .andExpect(jsonPath("$._embedded.boletaDtoList[1]._links.listar").exists());
    }

    @Test
    public void testListarBoletasHateoasVacio() throws Exception {
        // Arrange
        when(boletaService.listarBoletas()).thenReturn(Collections.emptyList());

        // Act & Assert
        mockMvc.perform(get("/api/v2/boleta/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded").doesNotExist());
    }
}