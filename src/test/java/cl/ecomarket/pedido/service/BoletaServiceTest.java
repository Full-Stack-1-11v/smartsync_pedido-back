package cl.ecomarket.pedido.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import cl.ecomarket.pedido.dto.BoletaDto;
import cl.ecomarket.pedido.dto.PedidoDto;
import cl.ecomarket.pedido.dto.ProductoDto;
import cl.ecomarket.pedido.dto.UserDto;

@SpringBootTest
@ActiveProfiles("test")
public class BoletaServiceTest {

    private BoletaService boletaService;

    @BeforeEach
    void setUp() {
        boletaService = new BoletaService();
    }

    @Test
    void testGuardarBoleta() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        // When
        BoletaDto result = boletaService.guardarBoleta(boleta);
        boletaService.guardarBoleta(boleta);

        // Then
        assertNotNull(result);
        assertEquals(pedido, result.getPedido());
        assertEquals(producto, result.getProducto());
        assertEquals(user, result.getUser());
    }

    @Test
    void testListarBoletas() {
        // Given
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

        boletaService.guardarBoleta(boleta1);
        boletaService.guardarBoleta(boleta2);

        // When
        List<BoletaDto> result = boletaService.listarBoletas();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(pedido1, result.get(0).getPedido());
        assertEquals(producto1, result.get(0).getProducto());
        assertEquals(user1, result.get(0).getUser());
        assertEquals(pedido2, result.get(1).getPedido());
        assertEquals(producto2, result.get(1).getProducto());
        assertEquals(user2, result.get(1).getUser());
    }
}
