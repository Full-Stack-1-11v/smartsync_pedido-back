package cl.ecomarket.pedido.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;

public class PedidoTest {

    @Test
    public void testConstructorConArgumentos() {
        // Given
        Long pedidoId = 1L;
        boolean estadoPedido = true;
        Date fechaPedido = Date.valueOf("2025-06-05");

        // When
        Pedido pedido = new Pedido(pedidoId, estadoPedido, fechaPedido);

        // Then
        assertNotNull(pedido);
        assertEquals(pedidoId, pedido.getPedidoId());
        assertEquals(estadoPedido, pedido.isEstadoPedido());
        assertEquals(fechaPedido, pedido.getFechaPedido());
    }

    @Test
    public void testConstructorSinArgumentos() {
        // When
        Pedido pedido = new Pedido();

        // Then
        assertNotNull(pedido);
        assertEquals(null, pedido.getPedidoId());
        assertEquals(false, pedido.isEstadoPedido());
        assertEquals(null, pedido.getFechaPedido());
    }

    @Test
    public void testSettersAndGetters() {
        // Given
        Pedido pedido = new Pedido();
        Long pedidoId = 2L;
        boolean estadoPedido = false;
        Date fechaPedido = Date.valueOf("2025-06-05");

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
    public void testEqualsYHashCode() {
        // Given
        Pedido pedido1 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido2 = new Pedido(1L, true, Date.valueOf("2025-06-05"));

        // Then
        assertEquals(pedido1, pedido2);
        assertEquals(pedido1.hashCode(), pedido2.hashCode());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testEqualsComparativos() {
        // Given
        Pedido pedido1 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido2 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido3 = new Pedido(2L, false, Date.valueOf("2025-06-06"));

        // When & Then
        assertTrue(pedido1.equals(pedido1)); // Mismo objeto
        assertTrue(pedido1.equals(pedido2)); // Objetos iguales
        assertFalse(pedido1.equals(pedido3)); // Objetos diferentes
        assertFalse(pedido1.equals(null)); // Comparación con null
        assertFalse(pedido1.equals("Hola")); // Comparación con otro tipo
    }

    @Test
    public void testConsistenciaHashCode() {
        // Given
        Pedido pedido1 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido2 = new Pedido(1L, true, Date.valueOf("2025-06-05"));
        Pedido pedido3 = new Pedido(2L, false, Date.valueOf("2025-06-06"));

        // When & Then
        assertEquals(pedido1.hashCode(), pedido2.hashCode()); // Objetos iguales, mismo hashCode
        assertNotEquals(pedido1.hashCode(), pedido3.hashCode()); // Objetos diferentes, hashCode diferente
        assertNotNull(pedido1.hashCode()); // hashCode no debe ser null
    }

    @Test
    public void testToString() {
        // Given
        Pedido pedido = new Pedido(1L, true, Date.valueOf("2025-06-05"));

        // When
        String result = pedido.toString();

        // Then
        assertTrue(result.contains("pedidoId=1"));
        assertTrue(result.contains("estadoPedido=true"));
        assertTrue(result.contains("fechaPedido=2025-06-05"));
    }
}
