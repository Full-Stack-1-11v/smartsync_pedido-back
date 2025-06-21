package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class PedidoDtoTest {

    @Test
    public void testAllArgsConstructor() {
        // Given
        Long pedidoId = 1L;
        boolean estadoPedido = true;
        Date fechaPedido = Date.valueOf("2025-06-05");

        // When
        PedidoDto pedido = new PedidoDto(pedidoId, estadoPedido, fechaPedido);

        // Then
        assertEquals(pedidoId, pedido.getPedidoId());
        assertEquals(estadoPedido, pedido.isEstadoPedido());
        assertEquals(fechaPedido, pedido.getFechaPedido());
    }

    @Test
    public void testNoArgsConstructor() {
        // When
        PedidoDto pedido = new PedidoDto();

        // Then
        assertEquals(null, pedido.getPedidoId());
        assertEquals(false, pedido.isEstadoPedido());
        assertEquals(null, pedido.getFechaPedido());
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        PedidoDto pedido = new PedidoDto();
        Long pedidoId = 2L;
        boolean estadoPedido = false;
        Date fechaPedido = Date.valueOf("2025-06-06");

        // When
        pedido.setPedidoId(pedidoId);
        pedido.setEstadoPedido(estadoPedido);
        pedido.setFechaPedido(fechaPedido);

        // Then
        assertEquals(pedidoId, pedido.getPedidoId());
        assertEquals(estadoPedido, pedido.isEstadoPedido());
        assertEquals(fechaPedido, pedido.getFechaPedido());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido3 = new PedidoDto(2L, false, Date.valueOf("2025-06-06"));

        // Then
        assertEquals(pedido1, pedido2);
        assertEquals(pedido1.hashCode(), pedido2.hashCode());
        assertNotEquals(pedido1, pedido3);
        assertNotEquals(pedido1.hashCode(), pedido3.hashCode());
    }

    @Test
    public void testToString() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));

        // When
        String result = pedido.toString();

        // Then
        String expected = "PedidoDto(pedidoId=1, estadoPedido=true, fechaPedido=2025-06-05)";
        assertEquals(expected, result);
    }
}
