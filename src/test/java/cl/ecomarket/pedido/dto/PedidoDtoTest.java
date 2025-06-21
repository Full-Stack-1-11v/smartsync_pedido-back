package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    public void testEqualsSameObject() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));

        // Then
        assertTrue(pedido.equals(pedido)); // Mismo objeto
    }

    @Test
    public void testEqualsDifferentObjectSameValues() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));

        // Then
        assertTrue(pedido1.equals(pedido2)); // Objetos diferentes, mismos valores
        assertTrue(pedido2.equals(pedido1)); // Objetos diferentes, mismos valores
    }

    @Test
    public void testEqualsDifferentValues() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(2L, false, Date.valueOf("2025-06-06"));

        // Then
        assertFalse(pedido1.equals(pedido2)); // Valores Diferentes
        assertFalse(pedido2.equals(pedido1)); // Valores Diferentes
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));

        // Then
        assertFalse(pedido.equals(null)); // Objeto Null
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        String differentClassObject = "Not a PedidoDto";

        // Then
        assertFalse(pedido.equals(differentClassObject)); // Clase diferente
    }

    @Test
    public void testHashCodeSameValues() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));

        // Then
        assertEquals(pedido1.hashCode(), pedido2.hashCode()); // Mismos valores, mismo hashCode
    }

    @Test
    public void testHashCodeDifferentValues() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(2L, false, Date.valueOf("2025-06-06"));

        // Then
        assertNotEquals(pedido1.hashCode(), pedido2.hashCode()); // Valores diferentes, hashCode diferente
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

    @Test
    public void testEqualsWithNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(null, false, null);
        PedidoDto pedido2 = new PedidoDto(null, false, null);

        // Then
        assertTrue(pedido1.equals(pedido2)); // Atributos null, mismos valores
    }

    @Test
    public void testEqualsPartialNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, null);
        PedidoDto pedido2 = new PedidoDto(1L, true, null);

        // Then
        assertTrue(pedido1.equals(pedido2)); // Atributos parciales null, mismos valores
    }

    @Test
    public void testEqualsDifferentPartialNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, null);
        PedidoDto pedido2 = new PedidoDto(1L, false, null);

        // Then
        assertFalse(pedido1.equals(pedido2)); // Atributos parciales null, diferentes valores
    }

    @Test
    public void testHashCodeWithNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(null, false, null);
        PedidoDto pedido2 = new PedidoDto(null, false, null);

        // Then
        assertEquals(pedido1.hashCode(), pedido2.hashCode()); // Atributos null, hashCode igualado
    }

    @Test
    public void testHashCodePartialNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, null);
        PedidoDto pedido2 = new PedidoDto(1L, true, null);

        // Then
        assertEquals(pedido1.hashCode(), pedido2.hashCode()); // Atributos parciales null, hashCode igualado
    }

    @Test
    public void testHashCodeDifferentPartialNullAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, null);
        PedidoDto pedido2 = new PedidoDto(1L, false, null);

        // Then
        assertNotEquals(pedido1.hashCode(), pedido2.hashCode()); // Atributos parciales null, hashCode distinto
    }

    @Test
    public void testToStringConAtributosNull() {
        // Given
        PedidoDto pedido = new PedidoDto(null, false, null);

        // When
        String result = pedido.toString();

        // Then
        assertTrue(result.contains("pedidoId=null"));
        assertTrue(result.contains("estadoPedido=false"));
        assertTrue(result.contains("fechaPedido=null"));
    }

    @Test
    public void testEqualsConAtributosMixtos() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(1L, true, null);

        // Then
        assertFalse(pedido1.equals(pedido2)); // Atributos mixtos, diferentes valores
    }

    @Test
    public void testHashCodeWithMixedAttributes() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, Date.valueOf("2025-06-05"));
        PedidoDto pedido2 = new PedidoDto(1L, true, null);

        // Then
        assertNotEquals(pedido1.hashCode(), pedido2.hashCode()); // Atributos mixtos, hashCode distinto
    }
}
