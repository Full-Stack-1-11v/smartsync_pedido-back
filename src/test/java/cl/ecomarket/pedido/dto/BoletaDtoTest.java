package cl.ecomarket.pedido.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
public class BoletaDtoTest {

    @Test
    public void testSettersAndGetters() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();

        // When
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        // Then
        assertEquals(pedido, boleta.getPedido());
        assertEquals(producto, boleta.getProducto());
        assertEquals(user, boleta.getUser());
    }

    @Test
    public void testEqualsSameObject() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        // Then
        assertTrue(boleta.equals(boleta)); // Same object
    }

    @Test
    public void testEqualsDifferentValues() {
        // Given
        PedidoDto pedido1 = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto1 = new ProductoDto(1L, "Tomate", 400);
        UserDto user1 = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        PedidoDto pedido2 = new PedidoDto(2L, false, java.sql.Date.valueOf("2021-03-12"));
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

        // Then
        assertFalse(boleta1.equals(boleta2)); // Different values
    }

    @Test
    public void testEqualsNullObject() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        // Then
        assertFalse(boleta.equals(null)); // Null object
    }

    @Test
    public void testEqualsDifferentClass() {
        // Given
        PedidoDto pedido = new PedidoDto(1L, true, java.sql.Date.valueOf("2025-06-05"));
        ProductoDto producto = new ProductoDto(1L, "Tomate", 400);
        UserDto user = new UserDto(1, "John Doe", "john.doe@ejemplo.com");

        BoletaDto boleta = new BoletaDto();
        boleta.setPedido(pedido);
        boleta.setProducto(producto);
        boleta.setUser(user);

        String differentClassObject = "Not a BoletaDto";

        // Then
        assertFalse(boleta.equals(differentClassObject)); // Different class
    }

    @Test
    public void testHashCodeDifferentValues() {
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

        // Then
        assertNotEquals(boleta1.hashCode(), boleta2.hashCode()); // Different values, different hashCode
    }
}